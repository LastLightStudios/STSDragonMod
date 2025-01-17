package code.deprecatedcards;

import basemod.helpers.TooltipInfo;
import code.actions.ShedAction;
import code.cards.AbstractEasyCard;
import code.util.DragonUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.List;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.atb;

public class StretchYourLegs extends AbstractEasyCard {
    public final static String ID = makeID("StretchYourLegs");

    private final static int ENERGY_GAIN = 2;
    private final static int WEAK_GAIN = 2;
    private final static int UPGRADE_WEAK_GAIN = -1;
    private final static int SHED_VALUE = 1; // this do be hardcoded

    public StretchYourLegs() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ENERGY_GAIN;
        secondMagic = baseSecondMagic = WEAK_GAIN;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if (upgraded){
            return DragonUtils.getSheddableDebuffTooltips();
        } else {
            return null;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new WeakPower(p, secondMagic, false));
        if (upgraded){
            atb(new ShedAction(p, SHED_VALUE));
        }
    }

    public void upp() {
        //upgradeMagicNumber();
        //upgradeSecondMagic(UPGRADE_WEAK_GAIN);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}