package tasks;

import dev.alnat.practice.tasks.order.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by @author AlNat on 13.08.2020.
 * Licensed by Apache License, Version 2.0
 */
public class OrderTest {

    @Test
    public void simpleTest() {
        String in = "сапог сарай арбуз болт бокс биржа";
        Data d = new Data(in);
        Assert.assertEquals("[б=[биржа, бокс, болт], c=[сапог, сарай]]", d.toString());
    }

}
