public class numero {

    public static final numero ZERO = new numero('+', '0');
    private char[] raw_value;
    private char sign = '+';
    private int post_comma_digits = 0;
    public String label; //etichetta per scopi futuri

    ///COSTRUTTORI/////

    // standard constructor accepts sign, decimal digits amount and a char array of digits
    public numero(char s, int postcomma, char[] digits)
    {
        this.sign = s;
        this.raw_value = digits;
        this.post_comma_digits = postcomma;
        //this.update();

    }

    // constructor to create a number that is a copy of another one
    public numero(numero input)
    {
        input.copy(this);
    }

    // constructor to create a empty number with defined sign and length
    public numero(char s, int length)
    {
        this.sign = s;
        this.raw_value = new char[length];
        for(int i = 0; i < this.raw_value.length; i++) this.raw_value[i] = '0';
    }

    // constructor to create a single digit number
    public numero(char s, char d)
    {
        this.sign = s;
        this.raw_value = new char[1];
        this.raw_value[0] = d;
    }

    // get valid char array from input string to call constructor
    public static numero formatNumber(String input)
    {
        //checking zero
        boolean isZero = true;
        for(int i = 0; i < input.length(); i++)
            if(baseOP.isDigit(input.charAt(i)) && input.charAt(i) != '0') isZero = false;
        if (isZero) return getZero();

        //numero number;
        int size = 0;
        char[] digits;
        char s;
        int num_postcomma = 0;

        //checking sign
        if (input.charAt(0) == '-') s = '-';
        else s = '+';

        //counting number of digits
        boolean firstNonZero = false;
        int firstdigit = 0;
        for (int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == '.') break;
            if (baseOP.isDigit(input.charAt(i)) && !firstNonZero && input.charAt(i) != '0')
            {
                firstNonZero = true;
                firstdigit = i;
            }
            if (baseOP.isDigit(input.charAt(i)) && firstNonZero) size++;
        }

        if(input.indexOf('.') != -1)
            for(int i = input.indexOf('.'); i < input.length(); i++)
                if(baseOP.isDigit(input.charAt(i))) num_postcomma++;

        // creation of arrays
        size = size + num_postcomma;
        digits = new char[size];

        int index = 0;

        if (size == num_postcomma)
            firstdigit = input.indexOf('.')+1;

        for (int i = firstdigit; i < input.length(); i++)
        {
            if (baseOP.isDigit(input.charAt(i)))
            {
                digits[index] = input.charAt(i);
                index++;
            }
        }

        return new numero(s, num_postcomma, digits);
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


    // multiplies (pos>0) and divides (pos<0) by a factor of 10(1/-1)-100(2/-2)-1000(3/-3) etc
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

    // a function that set amount of decimal digits
    public void set_post_comma(int val)
    {
        if(val <= this.raw_value.length)
        {
            this.post_comma_digits = val;
        }
    }

    // returns the numero object with more digits in integer part
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
        if(num1.post_comma_digits >= num2.post_comma_digits) return num2;
        else return num1;
    }

    // creates a copy of ZERO and returns that
    public static numero getZero()
    {
        return new numero(ZERO);
    }

    public void copy(numero dest)
    {
        char[] temp = new char[this.raw_value.length];
        for(int i = 0; i < this.raw_value.length; i++) temp[i] = this.raw_value[i];
        dest.raw_value = temp;
        dest.post_comma_digits = this.post_comma_digits;
        dest.sign     = this.sign;
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
