package net.minecraft.src;

import java.io.*;
import java.util.*;

public final class MyProp {
    public MyProp(String s) {
        lines = new ArrayList();
        props = new HashMap();
        a = s;
        File file = new File(a);
        if(file.exists())
        {
            try
            {
                load();
            }
            catch(IOException ioexception)
            {
                System.out.println((new StringBuilder()).append("[Props] Unable to load ").append(a).append("!").toString());
            }
        } else
        {
            save();
        }
    }

    public void load() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(a), "UTF8"));
        lines.clear();
        props.clear();
        String s;
        while((s = bufferedreader.readLine()) != null) 
        {
            s = new String(s.getBytes(), "UTF-8");
            char c = '\0';
            int j;
            for(j = 0; j < s.length() && Character.isWhitespace(c = s.charAt(j)); j++) { }
            if(s.length() - j == 0 || s.charAt(j) == '#' || s.charAt(j) == '!')
            {
                lines.add(s);
            } else
            {
                int k = j;
                boolean flag = s.indexOf('\\', j) != -1;
                StringBuffer stringbuffer = flag ? new StringBuffer() : null;
                if(stringbuffer != null)
                {
                    do
                    {
                        if(j >= s.length() || Character.isWhitespace(c = s.charAt(j++)) || c == '=' || c == ':')
                        {
                            break;
                        }
                        if(flag && c == '\\')
                        {
                            if(j == s.length())
                            {
                                s = bufferedreader.readLine();
                                if(s == null)
                                {
                                    s = "";
                                }
                                j = 0;
                                while(j < s.length() && Character.isWhitespace(c = s.charAt(j))) 
                                {
                                    j++;
                                }
                                continue;
                            }
                            c = s.charAt(j++);
                        }
                        switch(c)
                        {
                        case 110: // 'n'
                            stringbuffer.append('\n');
                            break;

                        case 116: // 't'
                            stringbuffer.append('\t');
                            break;

                        case 114: // 'r'
                            stringbuffer.append('\r');
                            break;

                        case 117: // 'u'
                            if(j + 4 <= s.length())
                            {
                                char c1 = (char)Integer.parseInt(s.substring(j, j + 4), 16);
                                stringbuffer.append(c1);
                                j += 4;
                            }
                            break;

                        case 111: // 'o'
                        case 112: // 'p'
                        case 113: // 'q'
                        case 115: // 's'
                        default:
                            stringbuffer.append('\0');
                            break;
                        }
                    } while(true);
                }
                boolean flag1 = c == ':' || c == '=';
                String s1;
                if(flag)
                {
                    s1 = stringbuffer.toString();
                } else
                if(flag1 || Character.isWhitespace(c))
                {
                    s1 = s.substring(k, j - 1);
                } else
                {
                    s1 = s.substring(k, j);
                }
                for(; j < s.length() && Character.isWhitespace(c = s.charAt(j)); j++) { }
                if(!flag1 && (c == ':' || c == '='))
                {
                    char c2;
                    for(j++; j < s.length() && Character.isWhitespace(c2 = s.charAt(j)); j++) { }
                }
                if(!flag)
                {
                    lines.add(s);
                } else
                {
                    StringBuilder stringbuilder = new StringBuilder(s.length() - j);
                    do
                    {
                        if(j >= s.length())
                        {
                            break;
                        }
                        char c3 = s.charAt(j++);
                        if(c3 == '\\')
                        {
                            if(j == s.length())
                            {
                                s = bufferedreader.readLine();
                                if(s == null)
                                {
                                    break;
                                }
                                char c4;
                                for(j = 0; j < s.length() && Character.isWhitespace(c4 = s.charAt(j)); j++) { }
                                stringbuilder.ensureCapacity((s.length() - j) + stringbuilder.length());
                                continue;
                            }
                            char c5 = s.charAt(j++);
                            switch(c5)
                            {
                            case 110: // 'n'
                                stringbuilder.append('\n');
                                break;

                            case 116: // 't'
                                stringbuilder.append('\t');
                                break;

                            case 114: // 'r'
                                stringbuilder.append('\r');
                                break;

                            case 117: // 'u'
                                if(j + 4 <= s.length())
                                {
                                    char c6 = (char)Integer.parseInt(s.substring(j, j + 4), 16);
                                    stringbuilder.append(c6);
                                    j += 4;
                                    break;
                                }
                                continue;

                            case 111: // 'o'
                            case 112: // 'p'
                            case 113: // 'q'
                            case 115: // 's'
                            default:
                                stringbuilder.append('\0');
                                break;
                            }
                        }
                        stringbuilder.append('\0');
                    } while(true);
                    lines.add((new StringBuilder()).append(s1).append("=").append(stringbuilder.toString()).toString());
                }
            }
        }
        bufferedreader.close();
    }

    public void save() {
        FileOutputStream fileoutputstream = null;
        try
        {
            fileoutputstream = new FileOutputStream(a);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            System.out.println((new StringBuilder()).append("[Props] Unable to open ").append(a).append("!").toString());
        }
        PrintStream printstream = null;
        try
        {
            printstream = new PrintStream(fileoutputstream, true, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            System.out.println((new StringBuilder()).append("[Props] Unable to write to ").append(a).append("!").toString());
        }
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = lines.iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if(s.trim().length() == 0)
            {
                printstream.println(s);
            } else
            if(s.charAt(0) == '#')
            {
                printstream.println(s);
            } else
            if(s.contains("="))
            {
                int j = s.indexOf('=');
                String s1 = s.substring(0, j).trim();
                if(props.containsKey(s1))
                {
                    String s2 = (String)props.get(s1);
                    printstream.println((new StringBuilder()).append(s1).append("=").append(s2).toString());
                    arraylist.add(s1);
                } else
                {
                    printstream.println(s);
                }
            } else
            {
                printstream.println(s);
            }
        }

        Iterator iterator1 = props.entrySet().iterator();
        do
        {
            if(!iterator1.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            if(!arraylist.contains(entry.getKey()))
            {
                printstream.println((new StringBuilder()).append((String)entry.getKey()).append("=").append((String)entry.getValue()).toString());
            }
        } while(true);
        printstream.close();
        try
        {
            props.clear();
            lines.clear();
            load();
        }
        catch(IOException ioexception)
        {
            System.out.println((new StringBuilder()).append("[Props] Unable to load ").append(a).append("!").toString());
        }
    }

    public Map returnMap() throws Exception {
        HashMap hashmap = new HashMap();
        BufferedReader bufferedreader = new BufferedReader(new FileReader(a));
        do
        {
            String s;
            if((s = bufferedreader.readLine()) == null)
            {
                break;
            }
            if(s.trim().length() != 0 && s.charAt(0) != '#' && s.contains("="))
            {
                int j = s.indexOf('=');
                String s1 = s.substring(0, j).trim();
                String s2 = s.substring(j + 1).trim();
                hashmap.put(s1, s2);
            }
        } while(true);
        bufferedreader.close();
        return hashmap;
    }

    public boolean containsKey(String s) {
        for(Iterator iterator = lines.iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            if(s1.trim().length() != 0 && s1.charAt(0) != '#' && s1.contains("="))
            {
                int j = s1.indexOf('=');
                String s2 = s1.substring(0, j);
                if(s2.equals(s))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public String getProperty(String s) {
        for(Iterator iterator = lines.iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            if(s1.trim().length() != 0 && s1.charAt(0) != '#' && s1.contains("="))
            {
                int j = s1.indexOf('=');
                String s2 = s1.substring(0, j).trim();
                String s3 = s1.substring(j + 1);
                if(s2.equals(s))
                {
                    return s3;
                }
            }
        }

        return "";
    }

    public void removeKey(String s) {
        Boolean boolean1 = Boolean.valueOf(false);
        if(props.containsKey(s))
        {
            props.remove(s);
            boolean1 = Boolean.valueOf(true);
        }
        try
        {
            for(int j = 0; j < lines.size(); j++)
            {
                String s1 = (String)lines.get(j);
                if(s1.trim().length() != 0 && s1.charAt(0) != '#' && s1.contains("="))
                {
                    int k = s1.indexOf('=');
                    String s2 = s1.substring(0, k).trim();
                    if(s2.equals(s))
                    {
                        lines.remove(j);
                        boolean1 = Boolean.valueOf(true);
                    }
                }
            }

        }
        catch(ConcurrentModificationException concurrentmodificationexception)
        {
            removeKey(s);
            return;
        }
        if(boolean1.booleanValue())
        {
            save();
        }
    }

    public boolean keyExists(String s) {
        try
        {
            return containsKey(s);
        }
        catch(Exception exception)
        {
            return false;
        }
    }

    public String i(String s) {
        if(containsKey(s))
        {
            return getProperty(s);
        } else
        {
            return "";
        }
    }

    public String i(String s, String s1) {
        if(containsKey(s))
        {
            return getProperty(s);
        } else
        {
            a(s, s1);
            return s1;
        }
    }

    public void a(String s, String s1) {
        props.put(s, s1);
        save();
    }

    public int getInt(String s) {
        if(containsKey(s))
        {
            return Integer.parseInt(getProperty(s));
        } else
        {
            return 0;
        }
    }

    public int getInt(String s, int j) {
        if(containsKey(s))
        {
            return Integer.parseInt(getProperty(s));
        } else
        {
            setInt(s, j);
            return j;
        }
    }

    public void setInt(String s, int j) {
        props.put(s, String.valueOf(j));
        save();
    }

    public double h(String s) {
        if(containsKey(s))
        {
            return Double.parseDouble(getProperty(s));
        } else
        {
            return 0.0D;
        }
    }

    public double h(String s, double d) {
        if(containsKey(s))
        {
            return Double.parseDouble(getProperty(s));
        } else
        {
            a(s, d);
            return d;
        }
    }

    public void a(String s, double d) {
        props.put(s, String.valueOf(d));
        save();
    }

    public long f(String s) {
        if(containsKey(s))
        {
            return Long.parseLong(getProperty(s));
        } else
        {
            return 0L;
        }
    }

    public long f(String s, long l) {
        if(containsKey(s))
        {
            return Long.parseLong(getProperty(s));
        } else
        {
            a(s, l);
            return l;
        }
    }

    public void a(String s, long l) {
        props.put(s, String.valueOf(l));
        save();
    }

    public boolean m(String s) {
        if(containsKey(s))
        {
            return Boolean.parseBoolean(getProperty(s));
        } else
        {
            return false;
        }
    }

    public boolean m(String s, boolean flag) {
        if(containsKey(s))
        {
            return Boolean.parseBoolean(getProperty(s));
        } else
        {
            a(s, flag);
            return flag;
        }
    }

    public void a(String s, boolean flag) {
        props.put(s, String.valueOf(flag));
        save();
    }

    private String a;
    private List lines;
    private Map props;
}

