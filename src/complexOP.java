public class complexOP{

    public static numero sum(numero add1, numero add2)
    {

        numero appoggio1 = new numero('+');
        numero appoggio2 = new numero('+');
        numero res       = new numero('+');

        add1.copy(appoggio1);
        add2.copy(appoggio2);

        if(appoggio1.post_comma() != 0 || appoggio2.post_comma() != 0)
        {

            if (appoggio1.post_comma() > appoggio2.post_comma())        appoggio2.left_shift(appoggio1.post_comma() - appoggio2.post_comma()); // appoggio2 less precise
            else if (appoggio1.post_comma() < appoggio2.post_comma())   appoggio1.left_shift(appoggio2.post_comma() - appoggio1.post_comma()); // appoggio1 less precise
        }

        int index;
        if(appoggio1.bottom_search('E') <= appoggio2.bottom_search('E')) index = appoggio1.bottom_search('E');
        else index = appoggio2.bottom_search('E');

        for (int i = 126; i >= index; i--)
        {
            if (baseOP.rip_somma(res.getChar(i), appoggio1.getChar(i)) == '1')
                res.putChar(baseOP.rip_somma(res.getChar(i),appoggio1.getChar(i)), i-1);

            res.putChar(baseOP.somma(res.getChar(i),appoggio1.getChar(i)), i);

            if (baseOP.rip_somma(res.getChar(i),appoggio2.getChar(i)) == '1')
            {
                res.putChar(baseOP.somma(res.getChar(i-1), baseOP.rip_somma(res.getChar(i),appoggio2.getChar(i))), i-1);
            }

            res.putChar(baseOP.somma(res.getChar(i),appoggio2.getChar(i)), i);
        }

    // dividing result by the precision of appoggio1 (or appoggio2, same thing) in order to change comma position
    res.factor10(-(appoggio1.post_comma()));
    
    res.update();
        
    return res;
    }

   /* public static numero mult(numero fact1, numero fact2)
    {
        numero res = new numero();

        int len1 = fact1.search('E'); //the position of the first 'E' gives us the length of the number
        int len2 = fact2.search('E');

        for (int i=len2-1; i >= 0; i--)
        {
            numero row = new numero();
            for (int ii=len1-1; ii >= 0; ii--)
            {
                if(baseOP.rip_moltp(fact1.raw_value[i], fact2.raw_value[ii]) != '0')

            }

        }
    return res;
    }*/

}
