public class test
{
    public static void main(String[] args)
    {
        numero test = new numero(args[0]);
        numero test1 = new numero(args[1]);

        numero risultato;

        //risultato = complexOP.sum(test, test1);
        //risultato = complexOP.subtraction(test, test1);
        risultato = complexOP.mult(test, test1);
        risultato.printnum();
        //System.out.println(numero.compare(test, '>', ' ', test1, false));

    }
}
