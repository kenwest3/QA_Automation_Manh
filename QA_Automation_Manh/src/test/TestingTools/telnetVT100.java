/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *//*


package TestingTools;

*/
/*      using System;
        using System.Collections.Generic;
        using System.ComponentModel;
        using System.Data;
        using System.Drawing;
        using System.Linq;
        using System.Text;
        using System.Threading.Tasks;
        using System.Windows.Forms;
        using System.Net;
        using System.IO;
        using System.Diagnostics;
        using libVT100;
        using Thought.Net.Telnet;
        using System.Net.Sockets;
        using System.Threading;
*//*


import com.sun.deploy.net.protocol.ProtocolType;
import com.sun.org.apache.xpath.internal.operations.*;
import org.apache.commons.net.telnet.TelnetClient;
import org.jboss.netty.handler.codec.embedder.EmbeddedSocketAddress;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.String;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
import java.nio.channels.SocketChannel;

*/
/**
 * Created by kwestberg on 4/8/2015.
 *//*

public class telnetVT100 {
    public OutputStreamWriter _writer;
    public InputStreamReader _reader;
    String sHost = "QAAS2";
    int sPort = 23;

    static void socket(InetAddress sHost ,Integer sPort ) {
        try {
            sHost = InetAddress.getByName("QAAS2");
            sPort = Integer.valueOf(23);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public telnetVT100( ) throws IOException { {

        TelnetClient telCLi = new TelnetClient();

        StringBuilder _escapeSequence = new StringBuilder();

        Socket socket = new Socket(sHost, sPort);
        //SocketAddress sHost = new SocketAddress(address);



            //Thought.Net.Telnet.TelnetClient tc = new TelnetClient(socket);


/*
            TelnetStream stream = new TelnetStream(tc);
            bool cancel = false;
            _reader=new

            InputStreamReader(stream);

            _writer=new

            StreamWriter(stream);

            _writer.AutoFlush=true;
            BackgroundWorker bw = new BackgroundWorker();
            bw.DoWork+=bw_DoWork;
            bw.RunWorkerAsync();


            WaitForText("Username");

            Write("kwestberg");

            WaitForText("Password");

            Write("123456");

            //ProcessDeviceAttributes();
            WaitForText("Selection");

            Write("99");
        }


        void ProcessDeviceAttributes() throws IOException {
            _writer.write(((char) 27) + "[?1;2c");

        }

        void Write(String val) throws IOException {
            _writer.write(val);
            _writer.write((char) 13);
        }


        void Write(String val, String WaitForValue)throws IOException
        {
            _writer.write(val);
            _writer.write((char) 13);
            WaitForText(WaitForValue);
        }




        private void WaitForText(String text)
        {
            String val = "";
            DateTime timeout = DateTime.Now.AddSeconds(60);
            while (true)
            {

                while (ReadBuffer.Count > 0)
                {
                    val = ReadBuffer.Dequeue();
                    if (System.Text.RegularExpressions.Regex.IsMatch(val, text, System.Text.RegularExpressions.RegexOptions.IgnoreCase) || System.Text.RegularExpressions.Regex.IsMatch(Line, text, System.Text.RegularExpressions.RegexOptions.IgnoreCase))
                    {
                        return;
                    }
                }

                Thread.Sleep(100);
                if (DateTime.Now > timeout)
                {
                    //Thread.CurrentThread.Abort();
                    MessageBox.Show("Did not find '" + text + "'");
                }
            }
        }


        void bw_DoWork(object sender, DoWorkEventArgs e)
        {
            Read();
        }


        Queue<String> ReadBuffer = new Queue<String>();

        String Line = "";
        private void OutputAll()
        {
            Char[] chars = new char[2048];
            int totalRead = 0;
            bool escapeCharReached = false;
            String escapeSeq = "";

            do
            {


                try
                {
                    totalRead = _reader.Read(chars, 0, chars.Length);
                }
                catch (Exception ex)
                {

                    throw;
                }
                foreach (var c in chars)
                {
                    if (escapeCharReached)
                    {
                        escapeSeq += c;
                        if (escapeSeq == "[c")
                        {
                            ProcessDeviceAttributes();
                            escapeCharReached = false;
                            escapeSeq = "";
                        }
                        else if (escapeSeq == "[Z")
                        {
                            ProcessDeviceAttributes();
                            escapeCharReached = false;
                            escapeSeq = "";
                        }
                        else if (escapeSeq.Length >= 3)
                        {
                            escapeCharReached = false;
                            //Line += escapeSeq;
                            escapeSeq = "";
                        }


                    }
                    else {
                        if ((c == 10 || c == 0) && Line.Length > 0)
                        {
                            lock (LockObj)
                            {
                                ReadBuffer.Enqueue(Line);
                                UpdateRTB(Line + Environment.NewLine);
                            }

                            Line = "";
                        }
                        else if (c == 27)
                        {
                            escapeCharReached = true;
                        }
                        else if (c != "\0".ToCharArray()[0])
                        {
                            Line += c;

                        }

                    }

                }
                chars = new char[2048];
            } while (true);
        }


        private void Read()
        {
            Char[] chars = new char[2048];
            int totalRead = 0;


            do
            {


                try
                {
                    totalRead = _reader.Read(chars, 0, chars.Length);
                }
                catch (Exception ex)
                {

                    throw;
                }
                foreach (var c in chars)
                {
                    ProcessChar(c);

                }
                chars = new char[2048];
            } while (true);
        }


        private StringBuilder _escapeSequence;
        private ProcessCharResult _processCharResult;

        public enum ProcessCharResult
        {
            Processed,
            Unsupported,
            Escaping
        }

        public void ProcessChar(char ch)
        {

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
                        Line += ch;
                    //_processCharResult = ProcessNormalChar(ch);  Add it to the line
                }
            }
            else
            {
                if (ch == '\0') return; //ƒV?[ƒPƒ“ƒX’†‚ÉNULL•¶Žš‚ª“ü‚Á‚Ä‚¢‚éƒP?[ƒX‚ªŠm”F‚³‚ê‚½
                _escapeSequence.Append(ch);
                bool end_flag = false; //escape sequence‚Ì?I‚í‚è‚©‚Ç‚¤‚©‚ðŽ¦‚·ƒtƒ‰ƒO
                if (_escapeSequence.Length == 1)
                { //ESC+‚P•¶Žš‚Å‚ ‚é?ê?‡
                    end_flag = ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '>' || ch == '=' || ch == '|' || ch == '}' || ch == '~';
                }
                else if (_escapeSequence[0] == ']')
                { //OSC‚Ì?I’[‚ÍBEL‚©ST(String Terminator)
                    end_flag = ch == 0x07 || ch == 0x9c;
                }
                else
                {
                    end_flag = ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '@' || ch == '~' || ch == '|' || ch == '{';
                }

                if (end_flag)
                { //ƒV?[ƒPƒ“ƒX‚Ì‚¨‚í‚è
                    char[] seq = _escapeSequence.ToString().ToCharArray();


                    char code = seq[0];
                    _processCharResult = ProcessCharResult.Unsupported; //ProcessEscapeSequence‚Å—áŠO‚ª—ˆ‚½Œã‚Å?ó‘Ô‚ªEscaping‚Í‚Ð‚Ç‚¢Œ‹‰Ê‚ð?µ‚­‚Ì‚Å
                    _processCharResult = ProcessEscapeSequence(code, seq, 1);
                    _escapeSequence.Remove(0, _escapeSequence.Length);

                }
                else
                    _processCharResult = ProcessCharResult.Escaping;
            }
        }


        protected ProcessCharResult ProcessEscapeSequence(char code, char[] seq, int offset)
        {
            String param;
            switch (code)
            {
                case '[':
                    if (seq.Length - offset - 1 >= 0)
                    {
                        param = new String(seq, offset, seq.Length - offset - 1);
                        return ProcessAfterCSI(param, seq[seq.Length - 1]);
                    }
                    break;
                //throw new UnknownEscapeSequenceException(String.Format("unknown command after CSI {0}", code));
                case ']':
                    if (seq.Length - offset - 1 >= 0)
                    {
                        param = new String(seq, offset, seq.Length - offset - 1);
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

        protected virtual ProcessCharResult ProcessAfterCSI(String param, char code)
        {

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
                case 'f': //f‚Í–{“–‚ÍxtermŒÅ—L
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
        }


        int CurrentLineNumber = 0;
        int TopLineNumber = 0;
        int CaretColumn = 0;

        protected virtual void ProcessDeviceStatusReport(String param)
        {
            String response;
            if (param == "5")
                response = " [0n"; //‚±‚ê‚ÅOK‚ÌˆÓ–¡‚ç‚µ‚¢
            else if (param == "6")
                response = String.Format(" [{0};{1}R", CurrentLineNumber - TopLineNumber + 1, CaretColumn + 1);
            else
                throw new Exception("DSR " + param);

            byte[] data = Encoding.ASCII.GetBytes(response);
            data[0] = 0x1B; //ESC
            _writer.Write(data);
        }

        protected virtual void ProcessDeviceAttributes(String param)
        {
            byte[] data = Encoding.ASCII.GetBytes(" [?1;2c"); //‚È‚ñ‚©‚æ‚­‚í‚©‚ç‚È‚¢‚ªMindTerm“™‚ð‚Ý‚é‚Æ‚±‚ê‚Å‚¢‚¢‚ç‚µ‚¢
            data[0] = 0x1B; //ESC
            _writer.Write(data);
        }

        protected virtual ProcessCharResult ProcessControlChar(char ch)
        {
            if (ch == '\n' || ch == 0xB)
            { //Vertical Tab‚ÍLF‚Æ“™‚µ‚¢
                //DoCarriageReturn();
                //DoLineFeed();
                lock (LockObj)
                {
                    ReadBuffer.Enqueue(Line);
                    UpdateRTB(Line + Environment.NewLine);
                    Line = "";
                }
                return ProcessCharResult.Processed;
            }
            else if (ch == '\r')
            {

                //DoCarriageReturn();
                //DoLineFeed();
                lock (LockObj)
                {
                    ReadBuffer.Enqueue(Line);
                    UpdateRTB(Line + Environment.NewLine);
                    Line = "";
                }
                return ProcessCharResult.Processed;
            }
            else if (ch == 0x07)
            {
                //_tag.Receiver.IndicateBell();
                return ProcessCharResult.Processed;
            }
            else if (ch == 0x08)
            {
                //?s“ª‚Å?A’¼‘O?s‚Ì––”ö‚ªŒp‘±‚Å‚ ‚Á‚½?ê?‡?s‚ð–ß‚·
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
            }
            else if (ch == 0x09)
            {
                //_manipulator.CaretColumn = GetNextTabStop(_manipulator.CaretColumn);
                return ProcessCharResult.Processed;
            }
            else if (ch == 0x0E)
            {
                return ProcessCharResult.Processed; //ˆÈ‰º‚Q‚Â‚ÍCharDecoder‚Ì’†‚Å?ˆ—?‚³‚ê‚Ä‚¢‚é‚Í‚¸‚È‚Ì‚Å–³Ž‹
            }
            else if (ch == 0x0F)
            {
                return ProcessCharResult.Processed;
            }
            else if (ch == 0x00)
            {
                return ProcessCharResult.Processed; //null char‚Í–³Ž‹ !!CR NUL‚ðCR LF‚Æ‚Ý‚È‚·Žd—l‚ª‚ ‚é‚ª?ACR LF CR NUL‚Æ‚­‚é‚±‚Æ‚à‚ ‚Á‚Ä“ï‚µ‚¢
            }
            else
            {
                //Debug.WriteLine("Unknown char " + (int)ch);
                //“K“–‚ÈƒOƒ‰ƒtƒBƒbƒN•\Ž¦‚Ù‚µ‚¢
                return ProcessCharResult.Unsupported;
            }
        }






        private void UpdateRTB(String text)
        {
            Invoke((MethodInvoker)(() =>
                    {
                            outputTbx.AppendText(
                                    text.Replace(((char)10).ToString(), Environment.NewLine)
                            );
            outputTbx.SelectionStart = outputTbx.Text.Length;
            outputTbx.ScrollToCaret();
            Invalidate();
            Refresh();
            }));

        }



        }
    }
*/
