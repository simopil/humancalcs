public class test
{
    public static void main(String[] args)
    {

    numero test = new numero(args[0]);
    numero test1 = new numero(args[3]);

    numero risultato;
    risultato = complexOP.sum(test, test1);
    //risultato = complexOP.mult(test, test1);
    //test.printarray();
    //test1.printarray();

        System.out.println("evaluating "+args[0]+" "+ args[1].charAt(0)+ " "+args[2].charAt(0)+" "+ args[3]);
        System.out.println(numero.compare(test, args[1].charAt(0), args[2].charAt(0), test1, false));
/*
    System.out.println("");
    test_copy.printnum();
    System.out.print(" + ");
    test_copy1.printnum();
    System.out.print(" = ");
    risultato.printnum();
    System.out.println("");
    System.out.println("");
*/

    }
}
