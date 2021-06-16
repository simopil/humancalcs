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

    public static numero subtraction(numero add1, numero add2)
    {
        numero mod_bigger = new numero('+');
        numero mod_little = new numero('+');
        numero        res;
        //make bigger(longer int part) is always on top in subtraction
        if(numero.compare(add1, '>', '=', add2, true)) {
            add1.copy(mod_bigger);
            add2.copy(mod_little);
            res = new numero('+');
        } else {
            add2.copy(mod_bigger);
            add1.copy(mod_little);
            res = new numero('-');
        }
        //eventually normalizes decimal part to align digits
        if(mod_bigger.post_comma() != 0 || mod_little.post_comma() != 0)
        {
            if (mod_bigger.post_comma() > mod_little.post_comma())        mod_little.left_shift(mod_bigger.post_comma() - mod_little.post_comma()); // mod_little less precise
            else if (mod_bigger.post_comma() < mod_little.post_comma())   mod_bigger.left_shift(mod_little.post_comma() - mod_bigger.post_comma()); // mod_bigger less precise
        }
        //cycle
        for(int i = 126; i > mod_bigger.bottom_search('E'); i--) {

            if(res.getChar(i) == '1') {
                if(baseOP.req_sottr(mod_bigger.getChar(i), '1')) {
                    res.putChar('1', i-1);
                    mod_bigger.putChar('9', i);
                } else {
                    mod_bigger.putChar(baseOP.sottr(mod_bigger.getChar(i), '1'), i);
                }
            }

            if(baseOP.req_sottr(mod_bigger.getChar(i), mod_little.getChar(i)))
                res.putChar('1', i-1);

            res.putChar(baseOP.sottr(mod_bigger.getChar(i), mod_little.getChar(i)), i);
        }
        // dividing result by the precision in order to change comma position
        res.factor10(-(mod_bigger.post_comma()));
        res.update();
        return res;
    }

    public static numero mult(numero fact1, numero fact2)
    {
        numero res = new numero('+');
        int len1 = fact1.bottom_search('E'); //the position of the first 'E' gives us the length of the number
        int len2 = fact2.bottom_search('E');

        for (int i = 126; i > len1; i--)
        {
            numero aux_row = new numero('+');

            for (int j = 126; j > len2; j--)
            {
                numero aux_op = new numero('+');

                aux_op.putChar(baseOP.moltp(fact1.getChar(i), fact2.getChar(j)), 126);
                aux_op.putChar(baseOP.rip_moltp(fact1.getChar(i), fact2.getChar(j)), 125);

                aux_op.factor10(126-j);
                aux_row = sum(aux_row, aux_op);

            }
            aux_row.factor10(126-i);
            res = sum(res, aux_row);
        }
        res.set_comma(fact1.post_comma() + fact2.post_comma());

        res.setSign(baseOP.moltp(fact1.getSign(), fact2.getSign()));

        res.update();

        return res;
    }

}
