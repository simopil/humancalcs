public class numero {

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
    }
    
    
    public numero()
    {
        this('+', "");
    }
    
    public numero(numero input)
    {
        this();
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
            if(this.virg_pos + times < this.raw_value.length) //avoid overflow
            {
                for(int i = 0; i<times; i++) 
                {
                    if(this.virg_pos == 127) this.left_shift(1);
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
    }
    
    // a function the normalizes the number of digits after the comma by adding zeros to the less precise
    /*public void normalizza(numero num)
    {
        // call: menopreciso.normalizza(preciso)
        if(num.post_comma() != 0)
        {
            this.left_shift(num.post_comma() - this.post_comma());
            this.virg_pos = this.virg_pos + (num.post_comma() - this.post_comma());
        }
    }*/
    
    // a function that returns the number of digits after the comma
    public int post_comma()
    {
        return 127 - this.virg_pos;
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
    
   
    
    public void copy(numero dest)
    {
        for(int i = 0; i < this.raw_value.length; i++) dest.raw_value[i] = this.raw_value[i];
        dest.virg_pos = this.virg_pos;
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
    
    public void right_shift(int pos)
    {
        for(int p = 0; p<pos; p++)
        {
            int end = 0;
            if(this.bottom_search('E') != -1) end = this.bottom_search('E');
            for(int i = 126; i>end; i--) this.raw_value[i+1] = this.raw_value[i];
            this.raw_value[127] = '0';
            this.virg_pos ++;
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
        for(int i = this.bottom_search('E')+1; i < 127 ; i++)
        {
            if(this.virg_pos == i) System.out.print(".");
            System.out.print(this.raw_value[i]);
        }
    }

}
