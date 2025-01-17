package code.powers.nestpowers;

import code.powers.AbstractEasyPower;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static code.ModFile.makeID;
import static code.util.Wiz.adp;
import static code.util.Wiz.applyToSelf;

public class ComfyNestPower extends AbstractEasyPower {

    public static final String POWER_ID = makeID("ComfyNestPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ComfyNestPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("combust");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        if (EnergyPanel.getCurrentEnergy() > 0){
            //applyToSelf(new DelayedEmberPower(adp(), EnergyPanel.getCurrentEnergy() * amount));
            applyToSelf(new DelayedEmberPower(adp(), amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    };
}
