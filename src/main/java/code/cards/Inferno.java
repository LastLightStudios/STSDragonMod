package code.cards;

import code.actions.PlayAllSparksAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.DragonCharacterFile.Enums.DRAGON_COLOR;
import static code.ModFile.makeID;
import static code.util.Wiz.atb;
import static code.util.Wiz.makeInHand;

public class Inferno extends AbstractEasyCard {
    public final static String ID = makeID("Inferno");


    public Inferno() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, DRAGON_COLOR);
        exhaust = true;
        cardsToPreview = new InfernalBreath();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayAllSparksAction(m));
        if (upgraded){
            AbstractCard c = (new InfernalBreath());
            c.upgrade();
            makeInHand(c);
        } else {
            makeInHand(new InfernalBreath());
        }
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        cardsToPreview.upgrade();
        initializeDescription();
    }
}