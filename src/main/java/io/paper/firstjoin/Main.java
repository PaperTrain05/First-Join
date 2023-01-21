package io.paper.firstjoin;

import com.golfing8.kore.FactionsKore;
import com.golfing8.kore.expansion.FactionsKoreExpansion;
import io.paper.firstjoin.feature.firstjoinmessage;

public final class Main extends FactionsKoreExpansion {


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.registerFeature((new firstjoinmessage(FactionsKore.get(), "first-join", "first join message", null, null)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
