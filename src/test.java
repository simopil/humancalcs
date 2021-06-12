public class test
{
    public static void main(String[] args)
    {
        numero test = new numero(args[0]);
        numero test1 = new numero(args[1]);

        //numero risultato = new numero();
    
        /*for(int i = 0; i<1000; i++)
        {
            risultato = complexOP.sum(risultato, test1);
            risultato.printarray();
        }
        */
        //risultato = complexOP.sum(test, test1);
        //risultato.printnum();
        System.out.println(numero.compare(test, '>', ' ', test1, false));
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
