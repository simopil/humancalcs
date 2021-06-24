public class test
{
    public static void main(String[] args)
    {
        numero num1 = numero.formatNumber(args[1]);
        numero num2 = numero.formatNumber(args[2]);

        switch(args[0])
        {
            case "comparison": { System.out.print(numero.compare(num1, '>', ' ', num2, false));                                      break; }
            case "sum"       : { numero risultato = complexOP.sum(num1, num2);                               risultato.printnum();   break; }
            case "sub"       : { numero risultato = complexOP.subtraction(num1, num2);                       risultato.printnum();   break; }
            case "mult"      : { numero risultato = complexOP.mult(num1, num2);                              risultato.printnum();   break; }
            case "algsum"    : { numero risultato = complexOP.algebraicSum(num1, num2, args[3].charAt(0));   risultato.printnum();   break; }
        }

    }
}
