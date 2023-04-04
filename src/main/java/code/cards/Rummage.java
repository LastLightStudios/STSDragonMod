package code.cards;

import code.actions.HoardCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.atb;

public class Rummage extends AbstractEasyCard {
    public final static String ID = makeID("Rummage");

    private final static int CARD_DRAW = 1;
    private final static int UPGRADE_CARD_DRAW = 1;
    private final static int CARDS_HOARDED = 1;
    private final static int UPGRADE_CARDS_HOARDED = 1;
    private final static int PRIDE_GAIN = 2; //this one is hard-coded as we've only got 2 magic numbers. I figured this is least likely to change

    public Rummage() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = CARD_DRAW;
        baseSecondMagic = secondMagic = CARDS_HOARDED;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new HoardCardAction(secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_CARD_DRAW);
        upgradeSecondMagic(UPGRADE_CARDS_HOARDED);
    }
}