/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by kwestberg on 4/16/2015.
 */
public class OpenVMSReader implements Runnable {
    public OpenVMSReader(InputStream Reader, OutputStream Writer) {
        _reader = Reader;
        _writer = Writer;
    }

    private Thread t;
    private String threadName = "mine";
    public InputStream _reader = null;
    public OutputStream _writer = null;
    public String Line = "";
    public static String orderNumber = "";
    private StringBuilder _escapeSequence = new StringBuilder();
    private ProcessCharResult _processCharResult;
    public List<String> Queue = Collections.synchronizedList(new ArrayList<String>());


    public void run() {
        try {
            Read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Start() {
        if (t ==null){
            t = new Thread(this, threadName);
            t.start();
        }
    }


    private void Read() throws Exception {
        char[] chars;
        byte[] byts = new byte[2048];
        int totalRead = 0;


        do
        {


            try
            {
                totalRead = _reader.read(byts, 0, byts.length);
                chars = new String(byts).toCharArray();
                byts = new byte[2048];
            }
            catch (Exception ex)
            {

                throw ex;
            }
            for (Character c : chars)//Variable Element ??
            {
                ProcessChar(c);

            }
        } while (true);
    }

    public enum ProcessCharResult
    {
        Processed,
        Unsupported,
        Escaping
    }

    public void ProcessChar(char ch) throws Exception {

        //if (_processCharResult != ProcessCharResult.Escaping)
        if (_processCharResult != ProcessCharResult.Escaping)
        {
            if (ch == 0x1B)
            {
                _processCharResult = ProcessCharResult.Escaping;
            }
            else
            {

                if (ch < 0x20 || (ch >= 0x80 && ch < 0xA0))
                    _processCharResult = ProcessControlChar(ch);
                else
                {
                    Line += ch;
                    System.out.print(ch);
                    if (Line.contains("Order #:")){
                        orderNumber = this.Line;
                    }
                }
                //_processCharResult = ProcessNormalChar(ch);  Add it to the line
            }
        }
        else
        {
            if (ch == '\0') return; //?V?[?P???X????NULL???????????????P?[?X???m?F????
            _escapeSequence.append(ch);
            Boolean end_flag = false; //escape sequence???I???????????????t???O
            if (_escapeSequence.length() == 1)
            { //ESC+?P?????????????
                end_flag = ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '>' || ch == '=' || ch == '|' || ch == '}' || ch == '~';
            }
            else if (_escapeSequence.charAt(0) == ']')
            { //OSC???I?[??BEL??ST(String Terminator)
                end_flag = ch == 0x07 || ch == 0x9c;
            }
            else
            {
                end_flag = ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '@' || ch == '~' || ch == '|' || ch == '{';
            }

            if (end_flag)
            { //?V?[?P???X??????
                char[] seq = _escapeSequence.toString().toCharArray();


                char code = seq[0];
                _processCharResult = ProcessCharResult.Unsupported; //ProcessEscapeSequence???O?????????????Escaping?????????????????
                _processCharResult = ProcessEscapeSequence(code, seq, 1);
                _escapeSequence.delete(0, _escapeSequence.length());
                //_escapeSequence.replace(0, _escapeSequence.length(), "");//not sure which string to use here  ==  KWESTBERG

            }
            else
                _processCharResult = ProcessCharResult.Escaping;
        }
    }


    protected ProcessCharResult ProcessEscapeSequence(char code, char[] seq, int offset) throws Exception {
        String param;
        switch (code)
        {
            case '[':
                if (seq.length - offset - 1 >= 0)
                {
                    param = new String(seq, offset, seq.length - offset - 1);
                    return ProcessAfterCSI(param, seq[seq.length - 1]);
                }
                break;
            //throw new UnknownEscapeSequenceException(String.Format("unknown command after CSI {0}", code));
            case ']':
                if (seq.length - offset - 1 >= 0)
                {
                    param = new String(seq, offset, seq.length - offset - 1);
                    //return ProcessAfterOSC(param, seq[seq.Length - 1]);
                    return ProcessCharResult.Processed;
                }
                break;
            case '=':
                //ChangeMode(TerminalMode.Application);
                return ProcessCharResult.Processed;
            case '>':
                //ChangeMode(TerminalMode.Normal);
                return ProcessCharResult.Processed;
            case 'E':
                //ProcessNextLine();
                return ProcessCharResult.Processed;
            case 'M':
                //ReverseIndex();
                return ProcessCharResult.Processed;
            case 'D':
                //Index();
                return ProcessCharResult.Processed;
            case '7':
                //SaveCursor();
                return ProcessCharResult.Processed;
            case '8':
                //RestoreCursor();
                return ProcessCharResult.Processed;
            case 'c':
                //FullReset();
                return ProcessCharResult.Processed;
        }
        return ProcessCharResult.Unsupported;
    }

    protected ProcessCharResult ProcessAfterCSI(String param, char code) throws Exception {

        switch (code)
        {
            case 'c':
                ProcessDeviceAttributes(param);
                break;
            case 'm': //SGR
                //ProcessSGR(param);
                break;
            case 'h':
            case 'l':
                //return ProcessDECSETMulti(param, code);
            case 'r':
                //if (param.Length > 0 && param[0] == '?')
                //    return ProcessRestoreDECSET(param, code);
                //else
                //    ProcessSetScrollingRegion(param);
                break;
            case 's':
                //return ProcessSaveDECSET(param, code);
            case 'n':
                ProcessDeviceStatusReport(param);
                break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
                //ProcessCursorMove(param, code);
                break;
            case 'H':
            case 'f': //f??{????xterm??L
                // ProcessCursorPosition(param);
                break;
            case 'J':
                //ProcessEraseInDisplay(param);
                break;
            case 'K':
                //ProcessEraseInLine(param);
                break;
            case 'L':
                //ProcessInsertLines(param);
                break;
            case 'M':
                //ProcessDeleteLines(param);
                break;
            default:
                return ProcessCharResult.Unsupported;
        }

        return ProcessCharResult.Processed;
    }//protected ProcessCharResult ProcessAfterCSI(String param, char code) throws Exception


    int CurrentLineNumber = 0;
    int TopLineNumber = 0;
    int CaretColumn = 0;

    protected void ProcessDeviceStatusReport(String param) throws Exception {
        String response;
        if (param == "5")
            response = " [0n"; //?????OK????????
        else if (param == "6")
            response = String.format(" [{0};{1}R", CurrentLineNumber - TopLineNumber + 1, CaretColumn + 1);
        else
            throw new Exception("DSR " + param);

        //byte[] data = Encoding.ASCII.GetBytes(response);
        byte[] data = response.getBytes("UTF-8");
        data[0] = 0x1B; //ESC
        //_writer.write(data);
    }

    protected void ProcessDeviceAttributes(String param) throws IOException {
        //byte[] data = Encoding.ASCII.GetBytes(" [?1;2c"); //????????????MindTerm???????????????????
        byte[] data = " [?1;2c".getBytes();
        data[0] = 0x1B; //ESC
        //_writer.write(data);
    }

    protected ProcessCharResult ProcessControlChar(char ch) {
        if (ch == '\n' || ch == 0xB) { //Vertical Tab??LF???????
            //DoCarriageReturn();
            //DoLineFeed();
            synchronized (this)
            {
                //ReadBuffer.enqueue(Line);
                UpdateRTB(Line + "\n"); //Environment.NewLine);
                Line = "";
            }
            return ProcessCharResult.Processed;
        } else if (ch == '\r') {

            //DoCarriageReturn();
            //DoLineFeed();
            synchronized (this)
            {
                //ReadBuffer.Enqueue(Line);
                UpdateRTB(Line + "\n");
                Line = "";
            }
            return ProcessCharResult.Processed;
        } else if (ch == 0x07) {
            //_tag.Receiver.IndicateBell();
            return ProcessCharResult.Processed;
        } else if (ch == 0x08) {
            //?s?????A???O?s????????p??????????????s????
            //if (_manipulator.CaretColumn == 0)
            //{
            //    TerminalDocument doc = GetDocument();
            //    int line = doc.CurrentLineNumber - 1;
            //    if (line >= 0 && doc.FindLineOrEdge(line).EOLType == EOLType.Continue)
            //    {
            //        doc.InvalidateLine(doc.CurrentLineNumber);
            //        doc.CurrentLineNumber = line;
            //        if (doc.CurrentLine == null)
            //            _manipulator.Clear(GetConnection().TerminalWidth);
            //        else
            //            _manipulator.Load(doc.CurrentLine, doc.CurrentLine.CharLength - 1);
            //        doc.InvalidateLine(doc.CurrentLineNumber);
            //    }
            //}
            //else
            //    _manipulator.BackCaret();

            return ProcessCharResult.Processed;
        } else if (ch == 0x09) {
            //_manipulator.CaretColumn = GetNextTabStop(_manipulator.CaretColumn);
            return ProcessCharResult.Processed;
        } else if (ch == 0x0E) {
            return ProcessCharResult.Processed; //????Q???CharDecoder??????????????????????????
        } else if (ch == 0x0F) {
            return ProcessCharResult.Processed;
        } else if (ch == 0x00) {
            return ProcessCharResult.Processed; //null char????? !!CR NUL??CR LF??????d?l???????ACR LF CR NUL?????????????????
        } else {
            //Debug.WriteLine("Unknown char " + (int)ch);
            //?K????O???t?B?b?N?\???????
            return ProcessCharResult.Unsupported;
        }

    }




    //@Override
    private void UpdateRTB(String text) {
        Queue.add(text);
        System.out.println();
    }




}
