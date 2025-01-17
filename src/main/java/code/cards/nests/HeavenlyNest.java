package code.cards.nests;

import code.powers.nestpowers.HeavenlyNestPower;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static code.ModFile.makeID;
import static code.util.Wiz.applyToSelf;
import static code.util.Wiz.makeInHand;

public class HeavenlyNest extends AbstractNestCard {
    public final static String ID = makeID("HeavenlyNest");
    private final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    private final static int ENERGY_PER_ENERGY = 1;
    private final static int MIRACLES_CREATED = 1;

    public HeavenlyNest() {
        super(ID, 1, CardRarity.RARE);
        baseMagicNumber = magicNumber = ENERGY_PER_ENERGY;
        baseSecondMagic = secondMagic = MIRACLES_CREATED;
        cardsToPreview = new Miracle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded){
            makeInHand(new Miracle(), secondMagic);
        }
        applyToSelf(new HeavenlyNestPower(p, magicNumber));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}