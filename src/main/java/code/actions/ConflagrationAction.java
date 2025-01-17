package code.actions;

import code.powers.CauterizePower;
import code.powers.EmberPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;
import static code.util.Wiz.*;

public class ConflagrationAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private boolean upgraded = false;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse = -1;
    private int emberMultiplier = -1;
    private int cauterizeMultiplier = -1;

    public ConflagrationAction(AbstractPlayer p, AbstractMonster m, boolean upgraded, boolean freeToPlayOnce, int energyOnUse, int emberMultiplier, int cauterizeMultiplier) {
        this.p = p;
        this.m = m;
        this.freeToPlayOnce = freeToPlayOnce;
        this.upgraded = upgraded;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.emberMultiplier = emberMultiplier;
        this.cauterizeMultiplier = cauterizeMultiplier;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        /**
         * this is leftover from when the upgrade took it from X to X + 1, now its 2X and 3X, and the upgrade is implicitly passed in via the multipliers
        if (this.upgraded)
            effect++;
         **/
        if (effect > 0) {
            applyToSelf(new EmberPower(p, effect * emberMultiplier));
            applyToEnemy(m, new CauterizePower(m, effect * cauterizeMultiplier));
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
