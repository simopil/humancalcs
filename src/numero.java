public class numero {

    public static final numero ZERO = new numero('+');
    private char[] raw_value = new char[128];
    private char sign = '+';
    private int  virg_pos = 127;
    public String label; //etichetta per scopi futuri

    ///COSTRUTTORI/////

    public numero(char s, String input)
    {
        this.sign = s;
        int arraypos = 126;
        for (int i = input.length()-1; i >= 0 ; i--)
        {
            if (arraypos == 0) break;
            if(input.charAt(i) == '+' || input.charAt(i) == '-')         this.sign = input.charAt(i);
            else if(input.charAt(i) == '.' || input.charAt(i) == ',')    this.virg_pos = arraypos+1;
            else
            {
                this.putChar(input.charAt(i), arraypos);
                arraypos--;
            }
        }
        for(int i = arraypos; i > 0; i--) raw_value[i] = 'E';
        this.update();
    }


    public numero(char s)
    {
        this.sign = s;
        this.virg_pos = 127;
        for(int i = 0; i < 126; i++) this.raw_value[i] = 'E';
        this.raw_value[126] = '0';

    }

    public numero(numero input)
    {
        input.copy(this);
    }

    public numero(String number)
    {
        this('+', number);
    }

    //////////////////

    ////////          ////////

    // usage: obj.putChar('6', 8)
    public char putChar(char c, int pos)
    {
        if (pos <=0 || pos > 127) return 'X';
        char old_val = this.raw_value[pos];
        this.raw_value[pos] = c;
        return old_val;
    }

    // a function that returns the character at position pos
    public char getChar(int pos)
    {
        if (pos >=0 && pos < 127)
            return this.raw_value[pos];
        else return 'X';
    }


    // multiplies and divides by a factor of 10-100-1000 etc
    public void factor10(int times)
    {
        if(times > 0)
        {
            if(times <= (127-this.virg_pos+this.bottom_search('E')+1))
            {
                for(int i = 0; i<times; i++)
                {
                    if(this.virg_pos == 127)
                        this.left_shift(1);
                    this.virg_pos++;
                }
            }
        }
        else if(times < 0)
        {
            if(this.virg_pos + times > 0) //avoid overflow
            {
                this.virg_pos = this.virg_pos + times;
            }
        }
        this.update();
    }

    // a function that returns the number of digits after the comma
    public int post_comma()
    {
        return 127 - this.virg_pos;
    }

    public void set_comma(int val)
    {
        if(val < 128)
        {
            this.virg_pos = 127-val;
            for(int i = this.bottom_search('E'); i >= this.virg_pos; i--)
                this.raw_value[i] = '0';
        }
    }

    // a function that returns which of the two numbers has the highest amount of digits after the comma
    public static numero getPrecisest(numero num1, numero num2)
    {

        if(num1.post_comma() >= num2.post_comma()) return num1;
        else return num2;
    }

    // a function that returns which of the two numbers has the least amount of digits after the comma
    public static numero getPreciseleast(numero num1, numero num2)
    {
        // calculates the number of digits after the comma by subtracting the position of the comma from the position
        // of the first occurrance of the 'E'
        if(num1.post_comma() >= num2.post_comma()) return num2;
        else return num1;
    }

    public static boolean compare(numero num1, char op, char eq, numero num2, boolean absolute)
    {

        int start1 = num1.virg_pos - num1.bottom_search('E') -1;
        int start2 = num2.virg_pos - num2.bottom_search('E') -1;

        if(!absolute) {
            if(num1.sign == '+' && num2.sign == '-') {
                if(op == '>') return true;
                if(op == '<') return false;
                if(op == '=') return false;
            }
            if(num1.sign == '-' && num2.sign == '+') {
                if(op == '<') return true;
                if(op == '>') return false;
                if(op == '=') return false;
            }
        }
        // compara il numero di cifre prima e dopo la virg_pos
        //if num1 has more digits on the left
        if (start1 > start2)
        {
            // compara i segni (se son due meno si inverte il risultato)
            if (!absolute && num1.sign == '-') {
                if(op == '<') return true;
                if(op == '>') return false;
                if(op == '=') return false;
            }
            if(op == '>') return true;
            if(op == '<') return false;
            if(op == '=') return false;
        }
        //if num2 has more digits on the left
        if (start1 < start2)
        {
            // compara i segni (se son due meno si inverte il risultato)
            if (!absolute && num1.sign == '-') {
                if(op == '>') return true;
                if(op == '<') return false;
                if(op == '=') return false;
            }
            if(op == '>') return false;
            if(op == '<') return true;
            if(op == '=') return false;
        }

        //if both have same size on the left, performs digit-by-digit comparison of non-decimal part
        for (int i = start1; i > 0; i--)
        {
            if (num1.raw_value[num1.virg_pos-i] != num2.raw_value[num2.virg_pos-i]) {
                if (baseOP.confronto(num1.raw_value[num1.virg_pos-i], num2.raw_value[num2.virg_pos-i], ((num1.sign == '-' && absolute) || num1.sign == '+'), false))
                {
                    if(op == '>') return true;
                    if(op == '<') return false;
                    if(op == '=') return false;
                } else {
                    if(op == '>') return false;
                    if(op == '<') return true;
                    if(op == '=') return false;
                }
            }
        }
        //if both are equals on the left, check digitwise on the right of the comma (if present)
        int tries = 0;
        if (num1.post_comma() > num2.post_comma()) tries = num1.post_comma();
        else tries = num2.post_comma();
        for(int i = 0; i < tries; i++)
        {
            if(num1.virg_pos+i == 127 && num2.virg_pos+i < 127) {
                if(num1.sign == '-' && !absolute)
                {
                    if(op == '>') return true;
                    if(op == '<') return false;
                    if(op == '=') return false;
                } else {
                    if(op == '>') return false;
                    if(op == '<') return true;
                    if(op == '=') return false;
                }
            }
            else if(num1.virg_pos+i < 127 && num2.virg_pos+i == 127) {
                if(num1.sign == '-' && !absolute)
                {
                    if(op == '>') return false;
                    if(op == '<') return true;
                    if(op == '=') return false;
                } else {
                    if(op == '>') return true;
                    if(op == '<') return false;
                    if(op == '=') return false;
                }
            } else {
                if (num1.raw_value[num1.virg_pos+i] != num2.raw_value[num2.virg_pos+i]) {
                    if (baseOP.confronto(num1.raw_value[num1.virg_pos+i], num2.raw_value[num2.virg_pos+i], ((num1.sign == '-' && absolute) || num1.sign == '+'), false))
                    {
                        if(op == '>') return true;
                        if(op == '<') return false;
                        if(op == '=') return false;

                    } else {

                        if(op == '>') return false;
                        if(op == '<') return true;
                        if(op == '=') return false;
                        }
                }
            }
        }
        //assumed that only exception is EQUALITY
        if(op == '=' || eq == '=') return true;
        else return false;
    }


    public void copy(numero dest)
    {
        for(int i = 0; i < this.raw_value.length; i++) dest.raw_value[i] = this.raw_value[i];
        dest.virg_pos = this.virg_pos;
        dest.sign     = this.sign;
        dest.update();
    }

    // searching a character in the array
    // usage : obj.search('value_to_find')
    // returns position of first occurance of character
    public int search(char value)
    {
        for(int i = 0; i < this.raw_value.length; i++)
        {
            if(this.raw_value[i] == value) return i;
        }
        return -1;
    }

    public int bottom_search(char value)
    {
        for(int i = this.raw_value.length-1; i>=0; i--)
        {
            if(this.raw_value[i] == value) return i;
        }
        return -1;
    }

    public void right_shift(int pos)
    {
        for(int p = 0; p<pos; p++)
        {
            int end = 0;
            if(this.bottom_search('E') != -1) end = this.bottom_search('E');
            for(int i = 125; i>end; i--) this.raw_value[i+1] = this.raw_value[i];
            this.raw_value[end+1] = 'E';
            if(this.virg_pos < 127) this.virg_pos ++;
        }
    }

    // left shfti digits of n=pos positions
    public void left_shift(int pos)
    {
        for(int p = 0; p<pos; p++)
        {
            int end = this.bottom_search('E');
            for(int i = end; i<126; i++) this.raw_value[i] = this.raw_value[i+1];
            this.raw_value[126] = '0';
            this.virg_pos --;
        }
    }

    public void update()
    {
        //deleting right zeroes if number isn't integer
        if(this.post_comma() != 0)
            while(this.getChar(126) == '0' && this.post_comma() != 0)
                this.right_shift(1);
        //deleting left zeroes before comma
        for(int i = this.bottom_search('E')+1; i < this.virg_pos; i++) {
            if(this.getChar(i) == '0') this.putChar('E', i);
            else break;
        }
        //adding zeroes between comma and first digit of decimal part
        for(int i = this.virg_pos; i <= this.bottom_search('E'); i++)
            this.raw_value[i] = '0';
        //assuring that ZERO has raw_pos[126] = '0' and not 'E'
        if(this.raw_value[126] == 'E') this.raw_value[126] = '0';
        //assuring that ZERO is never negative
        if(compare(this, '=', '=', ZERO, true))
            this.sign = '+';
    }

    public boolean isPositive()
    {
        if(this.sign == '+') return true;
        else return false;
    }

    public boolean isNegative()
    {
        if(this.sign == '-') return true;
        else return false;
    }

    public boolean haveSameSign(numero n)
    {
        if(this.sign == n.sign) return true;
        else return false;
    }

    public char getSign() {
        return this.sign;
    }
    public void setSign(char s) {
        this.sign = s;
    }
    ////////debugging///////
    public void printarray()
    {
        System.out.print("{ "+this.sign);
        for(int i = 0; i < this.raw_value.length; i++)
        {
            if(this.virg_pos == i) System.out.print(".");
            System.out.print(this.raw_value[i]);
        }
        System.out.print(" }");
        System.out.println("");
    }

    public void printnum()
    {
        if(this.sign != '+') System.out.print(this.sign);
        //if(this.bottom_search('E') == this.virg_pos-1) System.out.print("0");
        for(int i = this.bottom_search('E')+1; i < 127 ; i++)
        {
            if(this.virg_pos == i) System.out.print(".");
            System.out.print(this.raw_value[i]);
        }
    }

}
