public class numero {

    public static final numero ZERO = new numero('+', '0');
    private char[] raw_value;
    private char sign = '+';
    private int post_comma_digits = 0;
    public String label; //etichetta per scopi futuri

    ///COSTRUTTORI/////

    public numero(char s, int postcomma, char[] digits)
    {
        this.sign = s;
        this.raw_value = digits;
        this.post_comma_digits = postcomma;
        //this.update();

    }

    public numero(numero input)
    {
        input.copy(this);
    }

    public numero(char s, int length)
    {
        this.sign = s;
        this.raw_value = new char[length];
        for(int i = 0; i < this.raw_value.length; i++) this.raw_value[i] = '0';
    }

    // constructor to create a one position array
    public numero(char s, char d)
    {
        this.sign = s;
        this.raw_value = new char[1];
        this.raw_value[0] = d;
    }



    // usage: obj.putChar('6', 8)
    //logic: positive positions are integer part, negative pos are decimal part. Ex 235.325 in order: 2 1 0 -1 -2 -3
    public int putChar(char c, int pos)
    {
        if (pos < 0) {
            if(this.post_comma_digits >= -pos)
                this.raw_value[this.pre_comma()-1-pos] = c;
            else return -2;
        } else {
            if(pos < this.pre_comma())
                this.raw_value[this.pre_comma()-1-pos] = c;
            else return -1;
        }
        return 0;
    }

    // a function that returns the character at position pos
    public char getChar(int pos)
    {
        if (pos < 0) {
            if(this.post_comma_digits >= -pos)
                return this.raw_value[this.pre_comma()-1-pos];
            else return '0';
        } else {
            if(pos < this.pre_comma())
                return this.raw_value[this.pre_comma()-1-pos];
            else return '0';
        }
    }


    // multiplies and divides by a factor of 10-100-1000 etc
    public void factor10(int times)
    {
        if(times > 0)
        {
            if(times <= this.post_comma_digits)
                this.post_comma_digits = this.post_comma_digits - times;
            else {
                // temp is used to enlarge the original array
                char[] temp = new char[this.raw_value.length+(times-this.post_comma_digits)];
                for(int i = 0; i < raw_value.length; i++)
                    temp[i] = this.raw_value[i];
                for(int i = this.raw_value.length; i < temp.length; i++)
                    temp[i] = '0';
                this.raw_value = temp;
                this.post_comma_digits = 0;

            }
        }
        else if(times < 0)
        {
            if(-times <= this.raw_value.length - this.post_comma_digits)
                this.post_comma_digits = this.post_comma_digits - times;
            else {
                char[] temp = new char[ -times-this.post_comma_digits ];
                for(int i = 0; i < temp.length-this.raw_value.length; i++)
                    temp[i] = '0';
                for(int i = 0; i < this.raw_value.length; i++)
                    temp[i+temp.length-this.raw_value.length] = this.raw_value[i];
                this.raw_value = temp;
                this.post_comma_digits = this.raw_value.length;
            }
        }
        //this.update();
    }

    // a function that returns the number of digits after the comma
    public int post_comma()
    {
        return this.post_comma_digits;
    }

    // a function that returns the number of digits before the comma
    public int pre_comma()
    {
        return this.raw_value.length - this.post_comma_digits;
    }



    public void set_post_comma(int val)
    {
        if(val <= this.raw_value.length)
        {
            this.post_comma_digits = val;
        }
    }


    public static numero getLongestIntPart(numero num1, numero num2)
    {
        if(num1.pre_comma() >= num2.pre_comma()) return num1;
        else return num2;
    }
    // a function that returns which of the two numbers has the highest amount of digits after the comma
    public static numero getPrecisest(numero num1, numero num2)
    {

        if(num1.post_comma_digits >= num2.post_comma_digits) return num1;
        else return num2;
    }

    // a function that returns which of the two numbers has the least amount of digits after the comma
    public static numero getPreciseleast(numero num1, numero num2)
    {
        // calculates the number of digits after the comma by subtracting the position of the comma from the position
        // of the first occurrance of the 'E'
        if(num1.post_comma_digits >= num2.post_comma_digits) return num2;
        else return num1;
    }

    public static boolean compare(numero num1, char op, char eq, numero num2, boolean absolute)
    {

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
        if (num1.pre_comma() > num2.pre_comma())
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
        if (num1.pre_comma() < num2.pre_comma())
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
        for (int i = num1.pre_comma()-1; i >= 0; i--)
        {
            if (num1.getChar(i) != num2.getChar(i)) {
                if (baseOP.confronto(num1.getChar(i), num2.getChar(i), ((num1.sign == '-' && absolute) || num1.sign == '+'), false))
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
        for(int i = -1; i >= -tries; i--)
        {
            if(-i == num1.post_comma_digits && -i < num2.post_comma_digits) {
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
            else if(-i == num2.post_comma_digits && -i < num1.post_comma_digits) {
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
                if (num1.getChar(i) != num2.getChar(i)) {
                    if (baseOP.confronto(num1.getChar(i), num2.getChar(i), ((num1.sign == '-' && absolute) || num1.sign == '+'), false))
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
        char[] temp = new char[this.raw_value.length];
        for(int i = 0; i < this.raw_value.length; i++) temp[i] = this.raw_value[i];
        dest.raw_value = temp;
        dest.post_comma_digits = this.post_comma_digits;
        dest.sign     = this.sign;
        //dest.update();
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
    /*
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
    */
    // left shfti digits of n=pos positions
    /*public void left_shift(int pos)
    {
        for(int p = 0; p<pos; p++)
        {
            int end = this.bottom_search('E');
            for(int i = end; i<126; i++) this.raw_value[i] = this.raw_value[i+1];
            this.raw_value[126] = '0';
            this.virg_pos --;
        }
    }*/

    public void update()
    {
        //deleting left side useless zeroes
        int l_zeroes = 0;
        int r_zeroes = 0;
        if(this.pre_comma() != 0)
        {
            for (int i = 0; i < this.pre_comma(); i++)
            {
                if(this.raw_value[i] != '0') break;
                else l_zeroes++;
            }
        }

        // deleting right side useless zeroes
        if(this.post_comma_digits > 0)
        {
            for(int i = this.raw_value.length-1; i >= this.raw_value.length-1-this.post_comma_digits; i--)
            {
                if(this.raw_value[i] != '0') break;
                else r_zeroes++;
            }
            this.post_comma_digits = this.post_comma_digits - r_zeroes;
        }
        //checking zero
        if (this.raw_value.length-l_zeroes-r_zeroes == 0) 
            ZERO.copy(this);
        else 
        {
            char[] new_raw_value = new char[this.raw_value.length-l_zeroes-r_zeroes];
            for(int i = 0; i < new_raw_value.length; i++)
            {
                new_raw_value[i] = this.raw_value[i+l_zeroes];
            }
            this.raw_value = new_raw_value;
        }
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
        System.out.print("{ ");
        for(int i = 0; i < this.raw_value.length; i++)
        {
            System.out.print(this.raw_value[i]);
        }
        System.out.print(" }");
        System.out.println("\nsign: "+this.sign+" \ndigits after comma: "+this.post_comma_digits+"\nsize: "+ this.raw_value.length);
    }

    public void printnum()
    {
        if(this.sign != '+') System.out.print(this.sign);
        for(int i = this.pre_comma()-1; i >= 0; i--)
            System.out.print(this.getChar(i));
        if(this.post_comma_digits > 0) System.out.print(".");
        for(int i = -1; i >= -this.post_comma_digits; i--)
            System.out.print(this.getChar(i));
    }

}
