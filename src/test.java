public class test
{
    public static void main(String[] args)
    {
    
    
    
    numero test = new numero(args[0]);   
    numero test1 = new numero(args[1]);
    
    numero test_copy = new numero();
    numero test_copy1 = new numero();
    
    test.copy(test_copy);
    test1.copy(test_copy1);    
    /*if (test_copy.virg_pos > test_copy1.virg_pos) test_copy1.right_shift(test_copy.virg_pos-test_copy1.virg_pos);
    else if (test_copy.virg_pos < test_copy1.virg_pos) test_copy.right_shift( test_copy1.virg_pos-test_copy.virg_pos);
    numero.getPreciseleast(test_copy, test_copy1).normalizza(numero.getPrecisest(test_copy, test_copy1));
    
        */
    numero risultato;
    risultato = complexOP.sum(test, test1);
    //risultato = complexOP.mult(test, test1);
    
    
    System.out.println("");
    test_copy.printnum();
    System.out.print(" + ");
    test_copy1.printnum();
    System.out.print(" = ");
    risultato.printnum();
    System.out.println("");
    System.out.println("");
    
        /*char cnt = '0';
        while(baseOP.confronto('9', cnt, true, true))
        {
            char cnt2 = '0';
            System.out.println("");
            while(baseOP.confronto('9', cnt2, true, true)) 
                {
                    
                    if (baseOP.rip_moltp(cnt,cnt2) == '0') System.out.print(" ");
                    else System.out.print(baseOP.rip_moltp(cnt,cnt2));
                    System.out.print(baseOP.moltp(cnt,cnt2)+"   ");
                    if (cnt2 != '9' ) cnt2 = baseOP.somma(cnt2,'1');
                    else break;
                }
            if (cnt != '9' ) cnt = baseOP.somma(cnt,'1');
                else break;
       }
        System.out.println("");
        System.out.println("");
        
        System.out.print("+(+) = "+baseOP.moltp('+','+'));
        System.out.print("    +(-) = "+baseOP.moltp('+','-'));
        System.out.print("    -(+) = "+baseOP.moltp('-','+'));
        System.out.print("    -(-) = "+baseOP.moltp('-','-'));
        System.out.println("");
        System.out.println("");
        
        
        numero test = new numero(0,'+', args[0]);
        test.printarray();
        
        
        numero test1 = new numero(2, '+', "1111111");
        numero test2 = new numero(2, '+', "2222222");
        
        test1.printarray();
        test2.printarray();
        
        if(numero.getPrecisest(test1, test2) == test1) System.out.println("test1 più preciso");
        if(numero.getPrecisest(test1, test2) == test2) System.out.println("test2 più preciso");
        
        numero.getPreciseleast(test1, test2).normalizza(numero.getPrecisest(test1, test2));
        test1.printarray();
        test2.printarray();
        /*
        System.out.println("posizione virgola (gattino): "+test.virg_pos);
        System.out.println("segno (zodiacale): "+test.sign);
        System.out.println("Posizione prima 'E': "+test.search('E'));
        System.out.println("Shift a DX di 7 posizioni");
        System.out.println("");
        test.right_shift(7);
        test.printarray();
        System.out.println("posizione virgola (gattino): "+test.virg_pos);
        System.out.println("Posizione prima 'E': "+test.search('E'));
        System.out.println("segno (zodiacale): "+test.sign);
        */
    
    }
}
