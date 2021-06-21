import java.lang.Math;

public class test
{
    public static void main(String[] args)
    {

        numero num1 = formatNumber(args[0]);
        numero num2 = formatNumber(args[1]);
        numero risultato = complexOP.subtraction(num1, num2);
    /*
        numero num1 = formatNumber(args[0]);
        numero num2 = formatNumber(args[1]);

        numero risultato = complexOP.mult(num1, num2);
        /*while(true){
            risultato.printnum();
            System.out.println();
            risultato = complexOP.mult(risultato, risultato);
        }*/
        //System.out.println();
        //one.printarray();
        risultato.printnum();
        //System.out.println(numero.compare(num1, '>', ' ', num2, false));

    }

    public static numero formatNumber(String input)
    {
        //checking zero
        boolean isZero = true;
        for(int i = 0; i < input.length(); i++)
            if(baseOP.isDigit(input.charAt(i)) && input.charAt(i) != '0') isZero = false;
        if (isZero) return new numero('+', '0');

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
}
