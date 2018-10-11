package test.TestingTools;

import de.mud.telnet.TelnetWrapper;
import org.apache.commons.net.telnet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.lang.*;


/**
 * Created by kwestberg on 3/25/2015.
 */
public class OpenVMSConnector {

    //TelnetWrapper telCLi  = new TelnetWrapper();

    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream() ;    //these are defaulting to the JAVA IO
    public OutputStream _writer = telCLi.getOutputStream();

    public OpenVMSConnector() throws IOException {
        telCLi.connect("QAAS2", 23);


    }//public OpenVMSConnector()

    //public InputStream _reader = telCLi.getInputStream() ;    //these are defaulting to the JAVA IO
    //public OutputStream _writer = telCLi.getOutputStream();


    String userInput;

    public void Connect() throws InterruptedException, IOException {

        //TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT102", false, false, false, false);
        TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT102", false, false, false, false);
        //TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT102", false, false, true, false);
        EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);//super(1, initlocal, initremote, acceptlocal, acceptremote);
        //EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
        SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);//super(3, initlocal, initremote, acceptlocal, acceptremote);
        //SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);

        try {
            telCLi.addOptionHandler(ttopt);
            telCLi.addOptionHandler(echoopt);
            telCLi.addOptionHandler(gaopt);
        }
        catch (InvalidTelnetOptionException e)
        {
            System.err.println("Error registering option handlers: " + e.getMessage());
        }
        try {

            //telCLi.connect("QAAS2", 23);

            //_reader = telCLi.getInputStream();
            //_writer = telCLi.getOutputStream();
            Thread.sleep(5000);
            WaitForText("Username");
            _writer.write(new String("kwestberg\r").getBytes());
            //outstream.write(new String("\r").getBytes());
            _writer.flush();
            WaitForText("Password");
            _writer.write(new String("123456\r").getBytes());
            _writer.flush();
           /* WaitForText("[c");
            _writer.write(new String("\u001B7").getBytes());//"\u001B[?6c"
            _writer.flush();
            WaitForText("\u001B[255;255H");
            _writer.write(new String("\u001B[6n").getBytes());
            _writer.flush();
            WaitForText("\u001B8");
            _writer.write(new String("\u001B>").getBytes());
            _writer.flush();
            //D [0;1m*/
            Thread.sleep(5000);
            //D [3;80H

            WaitForText("\u001BD \u001B[3;80H");
            _writer.write(new String("\u001B[7;5H").getBytes());
            _writer.flush();
            WaitForText("\u001BD \u001B[0;1m");
            _writer.write(new String("\u001B[7;5H").getBytes());
            _writer.flush();
            WaitForText("\u001B[0;7m");
            _writer.write(new String("[7;80H").getBytes());
            _writer.flush();
            WaitForText("\u001B8");
            _writer.write(new String("\u001B>").getBytes());
            _writer.flush();
            WaitForText("[c");
            _writer.write(new String("\u001B7").getBytes());
            _writer.flush();
            WaitForText("\u001B[255;255H");
            _writer.write(new String("\u001B[6n").getBytes());
            _writer.flush();
            WaitForText("\u001B8");
            _writer.write(new String("\u001B>").getBytes());
            _writer.flush();
            WaitForText("Enter Your Selection:");
            _writer.write(new String("\\n").getBytes());
            _writer.flush();


            Thread.sleep(10000);
            //telnet.login("kwestberg", "123456");
            //telnet.setPrompt("user@host");
            //System.out.println(telnet.waitfor("Username:"));

            //FindText("Username");
            String servertext;

        } catch(Exception e) {
            e.printStackTrace();
        }
    }//   public void Connect

    private void WaitForText(String text) throws InterruptedException {
        String val = "";
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            {

                while (ReadBuffer.size() > 0) {
                    val = ReadBuffer.poll();
                    if (text.equals(text)) {
                        return;
                    }
                }

                Thread.sleep(1000);
                if (System.currentTimeMillis() > end) {
                    //Thread.CurrentThread.Abort();
                    System.out.println("Ken I Did not find '" + text + "'");
                }
            }
        }
    }
    void ProcessDeviceAttributes() throws IOException {
        //_writer.write(((char) 27) + "[?1;2c");
        _writer.write(Integer.parseInt(((char) 27) + "[?1;2c"));

    }//void ProcessDeviceAttributes() throws IOException

    void Write(String val) throws IOException {
        _writer.write(val.getBytes());
        _writer.write((char) 13);
    }//void Write(String val) throws IOException


    void Write(String val, String WaitForValue) throws IOException, InterruptedException {
        //_writer.write(val);
        _writer.write(val.getBytes());
        _writer.write((char) 13);
        WaitForText(WaitForValue);
    }//void Write(String val, String WaitForValue) throws IOException, InterruptedException



    void bw_DoWork(Object sender) throws IOException {
        _reader.read();
    }// void bw_DoWork(Object sender) throws IOException


    Queue<String> ReadBuffer = new Queue<String>() {
        public boolean add(String s) {
            return false;
        }

        public boolean offer(String s) {
            return false;
        }

        public String remove() {
            return null;
        }

        public String poll() {
            return null;
        }

        public String element() {
            return null;
        }

        public String peek() {
            return null;
        }

        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean contains(Object o) {
            return false;
        }

        public Iterator<String> iterator() {
            return null;
        }

        public Object[] toArray() {
            return new Object[0];
        }

        public <T> T[] toArray(T[] a) {
            return null;
        }

        public boolean remove(Object o) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return false;
        }

        public boolean addAll(Collection<? extends String> c) {
            return false;
        }

        public boolean removeAll(Collection<?> c) {
            return false;
        }

        public boolean retainAll(Collection<?> c) {
            return false;
        }

        public void clear() {

        }
    };

    String Line = "";
    private void OutputAll() throws Exception {
        char[] chars = new char[2048];
        int totalRead = 0;
        Boolean escapeCharReached = false;
        String escapeSeq = "";

        do
        {


            try
            {
                byte[] b = new byte[chars.length*2];
                for(int i=0; i<chars.length; i++) {
                    b[2*i] = (byte) ((byte) (chars[i]&0xFF00)>>8);
                    b[2*i+1] = (byte) (chars[i]&0x00FF);
                }

                totalRead = _reader.read(b, 0, chars.length);
            }
            catch (Exception ex)
            {

                throw ex;
            }
            for (Character c : chars)
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
                    else if (escapeSeq.length() >= 3)
                    {
                        escapeCharReached = false;
                        //Line += escapeSeq;
                        escapeSeq = "";
                    }


                }
                else {
                    if ((c.equals(10) || c.equals(0) && Line.length()  > 0))
                    {
                        synchronized  (this)
                        {
                            ReadBuffer.poll();//ReadBuffer.poll(Line)
                            UpdateRTB(Line + "\n");
                        }

                        Line = "";
                    }
                    else if (c.equals(27))  //27 is the ESC key or sequence
                    {
                        escapeCharReached = true;
                    }
                    else if (c != "\0".toCharArray()[0])//else if (c != "\0".toCharArray()[0])
                    {
                        Line += c;

                    }

                }

            }
            chars = new char[2048];
        } while (true);
    }


    private void Read() throws Exception {
        char[] chars = new char[2048];
        int totalRead = 0;


        do
        {


            try
            {
                totalRead = _reader.read(chars.toString().getBytes(), 0, chars.length);
            }
            catch (Exception ex)
            {

                throw ex;
            }
            for (Character c : chars)//Variable Element ??
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
                    Line += ch;
                //_processCharResult = ProcessNormalChar(ch);  Add it to the line
            }
        }
        else
        {
            if (ch == '\0') return; //�V?[�P���X����NULL�����������Ă���P?[�X���m�F���ꂽ
            _escapeSequence.append(ch);
            Boolean end_flag = false; //escape sequence��?I��肩�ǂ����������t���O
            if (_escapeSequence.length() == 1)
            { //ESC+�P�����ł���?�?�
                end_flag = ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '>' || ch == '=' || ch == '|' || ch == '}' || ch == '~';
            }
            else if (_escapeSequence.charAt(0) == ']')
            { //OSC��?I�[��BEL��ST(String Terminator)
                end_flag = ch == 0x07 || ch == 0x9c;
            }
            else
            {
                end_flag = ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ch == '@' || ch == '~' || ch == '|' || ch == '{';
            }

            if (end_flag)
            { //�V?[�P���X�̂����
                char[] seq = _escapeSequence.toString().toCharArray();


                char code = seq[0];
                _processCharResult = ProcessCharResult.Unsupported; //ProcessEscapeSequence�ŗ�O���������?�Ԃ�Escaping�͂Ђǂ����ʂ�?����̂�
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
            case 'f': //f�͖{����xterm�ŗL
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
            response = " [0n"; //�����OK�̈Ӗ��炵��
        else if (param == "6")
            response = String.format(" [{0};{1}R", CurrentLineNumber - TopLineNumber + 1, CaretColumn + 1);
        else
            throw new Exception("DSR " + param);

        //byte[] data = Encoding.ASCII.GetBytes(response);
        byte[] data = response.getBytes("UTF-8");
                data[0] = 0x1B; //ESC
        _writer.write(data);
    }

    protected void ProcessDeviceAttributes(String param) throws IOException {
        //byte[] data = Encoding.ASCII.GetBytes(" [?1;2c"); //�Ȃ񂩂悭�킩��Ȃ���MindTerm�����݂�Ƃ���ł����炵��
        byte[] data = " [?1;2c".getBytes();
                data[0] = 0x1B; //ESC
        _writer.write(data);
    }

    protected ProcessCharResult ProcessControlChar(char ch) {
        if (ch == '\n' || ch == 0xB) { //Vertical Tab��LF�Ɠ�����
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
            //?s����?A���O?s�̖������p���ł�����?�?�?s��߂�
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
            return ProcessCharResult.Processed; //�ȉ��Q��CharDecoder�̒���?��?����Ă���͂��Ȃ̂Ŗ���
        } else if (ch == 0x0F) {
            return ProcessCharResult.Processed;
        } else if (ch == 0x00) {
            return ProcessCharResult.Processed; //null char�͖��� !!CR NUL��CR LF�Ƃ݂Ȃ��d�l�����邪?ACR LF CR NUL�Ƃ��邱�Ƃ������ē��
        } else {
            //Debug.WriteLine("Unknown char " + (int)ch);
            //�K���ȃO���t�B�b�N�\���ق���
            return ProcessCharResult.Unsupported;
        }

    }




    //@Override
    private void UpdateRTB(String text)
    {
        new Thread(new Runnable() {
            public void run() {
                //outputTbx.AppendText(text.Replace(((char) 10).toString(), "\n").text.replace(char));
                //(((char) 10).ToString(), Environment.NewLine));
                //outputTbx.SelectionStart = outputTbx.Text.Length;
                //outputTbx.ScrollToCaret();
                //Invalidate();
                //Refresh();

            }
        }).start();

        }


    };//QueuusString BufferReader

//Class VMS Connector



/*



    private void UpdateRTB(String text)
    {
        Invoke((MethodInvoker) (() = >
                {
                        outputTbx.AppendText(
                                text.Replace(((char) 10).ToString(), Environment.NewLine)
                        );
        outputTbx.SelectionStart = outputTbx.Text.Length;
        outputTbx.ScrollToCaret();
        Invalidate();
        Refresh();
        }));

    }
    String FindText(String txt) {
        byte[] data = new byte[2048];
        int l = 0;
        String rtrn = "";
        boolean loop= true;
        while (loop) {
            try {
                l = telnet.read(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (l > 0) {
                try {
                    String decoded = new String(data, "UTF-8");
                    if (decoded.contains(txt)) loop = false;
                    rtrn += decoded;
                    System.out.print(decoded);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }

        return rtrn;

    }*/

/*

Ok, I traced a good old VT220 on VMS 7.2, and here is the interaction: (L
        denotes stuff sent by VMS, R is for stuff sent by terminal)
        L:48:10:26-   3 *[c                who are you ? (DA)
        156
        BB3

        R:48:10:61-  18 *[?62;1;2;6;7;8;9c
        153333333333333336
        BBF62B1B2B6B7B8B93

        62=level 3 (says VT300, but this is the response sent by a real VT220).
        1 = 132 columns capable
        2 = printer port
        6 = selective erase
        7 = soft character set
        8 = user defined keys
        9 = NRC sets

        possible responses:
<esc> [ ? 1 ; 2 c        VT100 terminal
<esc> [ ? 1 ; 0 c        VT101
<esc> [ ? 6 c                VT102 terminal
<esc> [ ? 62 ; 1 ; 2 ; 6 ; 7 ; 8 ; 9 ; 11 ; 14 c        VT220

        L:48:11:08-  18 *7*[255;255H*[6n*8
        131533333334153613
        B7BB255B2558BB6EB8

<esc>7 is save cursor position/state
<esc>[255,255H means put cursor at line 255 column 255.
<esc>[6n means ask current cursor position
<esc>* means restore to saved cursor position

        (in essence: put your cursor that the bottom most and right most position and
        tell me where it is, then go back to where you were

        R:48:11:53-   8 *[24;80R
        15333335
        BB24B802

        terminal responds with I am at line 24, column 80, essentially telling the
        host its screen size.

        For more information, you can go to http://www.vt100.org

*/