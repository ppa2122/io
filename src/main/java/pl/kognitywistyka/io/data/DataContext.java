package pl.kognitywistyka.io.data;

import pl.kognitywistyka.io.model.SimpleData;

/**
 * Created by pwilkin on 17.03.2022.
 */
public class DataContext {

    protected SimpleData simpleData;

    public SimpleData getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(SimpleData simpleData) {
        this.simpleData = simpleData;
    }
}
