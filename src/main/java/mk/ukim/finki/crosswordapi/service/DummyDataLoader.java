package mk.ukim.finki.crosswordapi.service;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.model.Word;
import mk.ukim.finki.crosswordapi.model.WordInCrossword;
import mk.ukim.finki.crosswordapi.model.enums.Difficulty;
import mk.ukim.finki.crosswordapi.model.enums.ExtensionDirection;
import mk.ukim.finki.crosswordapi.model.enums.Rarity;
import mk.ukim.finki.crosswordapi.repository.CrosswordRepository;
import mk.ukim.finki.crosswordapi.repository.WordInCrosswordRepository;
import mk.ukim.finki.crosswordapi.repository.WordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DummyDataLoader {
    private final CrosswordRepository crosswordRepository;
    private final Logger logger = LoggerFactory.getLogger(DummyDataLoader.class);
    private final WordRepository wordRepository;
    private final WordInCrosswordRepository wordInCrosswordRepository;

    public DummyDataLoader(CrosswordRepository crosswordRepository, WordRepository wordRepository, WordInCrosswordRepository wordInCrosswordRepository, WordInCrosswordRepository wordInCrosswordRepository1) {
        this.crosswordRepository = crosswordRepository;
        this.wordRepository = wordRepository;
        this.wordInCrosswordRepository = wordInCrosswordRepository1;
    }

    @PostConstruct
    public void init() {
        if (crosswordRepository.findByDateAssigned(LocalDate.now()).isEmpty()) {
            addCrossword();
        }
    }

    private void addCrossword() {
        Crossword crossword = new Crossword();
        crossword.setHeight(17);
        crossword.setWidth(14);
        crossword.setWordCount(91);
        crossword.setDifficulty(Difficulty.MEDIUM);
        crossword.setDateCreated(LocalDateTime.now());
        crossword.setDateAssigned(LocalDate.now());
        crossword.setActive(true);
        crossword = crosswordRepository.save(crossword);


        Word word1 = new Word();  // не е готов
        word1.setWord("ЛАТЕТАР");
        word1.setDefinition("Цевка за отстранување течност");
        word1.setLength(7);
        word1.setRarity(Rarity.COMMON);


        Word word2 = new Word();
        word2.setWord("АГОЛ");
        word2.setDefinition("Гледиште");
        word2.setLength(4);
        word2.setRarity(Rarity.COMMON);


        Word word3 = new Word();
        word3.setWord("М");
        word3.setDefinition("Милано");
        word3.setLength(1);
        word3.setRarity(Rarity.COMMON);


        Word word4 = new Word();
        word4.setWord("АЛ");
        word4.setDefinition("Аврил Лавињ");
        word4.setLength(2);
        word4.setRarity(Rarity.COMMON);


        Word word5 = new Word();
        word5.setWord("РУБИН");
        word5.setDefinition("Црвен скапоцен камен");
        word5.setLength(5);
        word5.setRarity(Rarity.COMMON);


        Word word6 = new Word();
        word6.setWord("АПАШ");
        word6.setDefinition("Крадец");
        word6.setLength(4);
        word6.setRarity(Rarity.COMMON);


        Word word7 = new Word();
        word7.setWord("К");
        word7.setDefinition("Калиум");
        word7.setLength(1);
        word7.setRarity(Rarity.COMMON);


        Word word8 = new Word();
        word8.setWord("АРСЕН");
        word8.setDefinition("Хемиски елемент, отров");
        word8.setLength(5);
        word8.setRarity(Rarity.COMMON);


        Word word9 = new Word();
        word9.setWord("РААТ");
        word9.setDefinition("Мир, спокој");
        word9.setLength(4);
        word9.setRarity(Rarity.COMMON);


        Word word10 = new Word();
        word10.setWord("АРТ");
        word10.setDefinition("Уметност (англ.)");
        word10.setLength(3);
        word10.setRarity(Rarity.COMMON);


        Word word11 = new Word();
        word11.setWord("ТОАЛЕТ");
        word11.setDefinition("Клозет");
        word11.setLength(6);
        word11.setRarity(Rarity.COMMON);


        Word word12 = new Word();
        word12.setWord("ЕКРАН");
        word12.setDefinition("Дел од телевизорот за гледање");
        word12.setLength(5);
        word12.setRarity(Rarity.COMMON);


        Word word13 = new Word(); // не е готов
        word13.setWord("ЛАМАРА");
        word13.setDefinition("Куп сено или слама");
        word13.setLength(6);
        word13.setRarity(Rarity.COMMON);


        Word word14 = new Word();
        word14.setWord("КАРАТЕ");
        word14.setDefinition("Борбена вештина");
        word14.setLength(6);
        word14.setRarity(Rarity.COMMON);


        Word word15 = new Word();
        word15.setWord("АЛАТИ");
        word15.setDefinition("Орудие за работа (мн.)");
        word15.setLength(5);
        word15.setRarity(Rarity.COMMON);


        Word word16 = new Word();
        word16.setWord("АГ");
        word16.setDefinition("Сребро");
        word16.setLength(2);
        word16.setRarity(Rarity.COMMON);


        Word word17 = new Word();
        word17.setWord("ЛУПА");
        word17.setDefinition("Стакло за зголемување");
        word17.setLength(4);
        word17.setRarity(Rarity.COMMON);


        Word word18 = new Word();
        word18.setWord("НАВАЛА");
        word18.setDefinition("Наплив");
        word18.setLength(6);
        word18.setRarity(Rarity.COMMON);


        Word word19 = new Word(); // не е готово
        word19.setWord("РАРЕК");
        word19.setDefinition("Виолинистката Агниеска");
        word19.setLength(5);
        word19.setRarity(Rarity.COMMON);


        Word word20 = new Word();
        word20.setWord("ТОА");
        word20.setDefinition("Мерка за маса и тежина");
        word20.setLength(3);
        word20.setRarity(Rarity.COMMON);


        Word word21 = new Word();
        word21.setWord("БАЛ");
        word21.setDefinition("Свечена вечерна игранка");
        word21.setLength(3);
        word21.setRarity(Rarity.COMMON);


        Word word22 = new Word();
        word22.setWord("САТАР");
        word22.setDefinition("Месарски нож");
        word22.setLength(5);
        word22.setRarity(Rarity.COMMON);


        Word word23 = new Word();  // не е готово
        word23.setWord("РАТОМИР");
        word23.setDefinition("Машко српско име");
        word23.setLength(7);
        word23.setRarity(Rarity.COMMON);


        Word word24 = new Word();
        word24.setWord("ЕЛА");
        word24.setDefinition("Зимзелено иглолисно дрво");
        word24.setLength(3);
        word24.setRarity(Rarity.COMMON);


        Word word25 = new Word();
        word25.setWord("ИШАРЕТ");
        word25.setDefinition("Знак, гест");
        word25.setLength(6);
        word25.setRarity(Rarity.COMMON);


        Word word26 = new Word();
        word26.setWord("ИРЕНА");
        word26.setDefinition("Актерката Ристиќ");
        word26.setLength(5);
        word26.setRarity(Rarity.COMMON);


        Word word27 = new Word();
        word27.setWord("ЛА");
        word27.setDefinition("Лос Ангелес");
        word27.setLength(2);
        word27.setRarity(Rarity.COMMON);


        Word word28 = new Word();
        word28.setWord("ТАРАБА");
        word28.setDefinition("Ограда од летви");
        word28.setLength(6);
        word28.setRarity(Rarity.COMMON);


        Word word29 = new Word();
        word29.setWord("Т");
        word29.setDefinition("Температура");
        word29.setLength(1);
        word29.setRarity(Rarity.COMMON);


        Word word30 = new Word();
        word30.setWord("ВИН");
        word30.setDefinition("Победа (англ.)");
        word30.setLength(3);
        word30.setRarity(Rarity.COMMON);


        Word word31 = new Word();
        word31.setWord("ТОП");
        word31.setDefinition("Артилериско оружје");
        word31.setLength(3);
        word31.setRarity(Rarity.COMMON);


        Word word32 = new Word();
        word32.setWord("ТАН");
        word32.setDefinition("Каша од сусам");
        word32.setLength(3);
        word32.setRarity(Rarity.COMMON);


        Word word33 = new Word();
        word33.setWord("МИГ");
        word33.setDefinition("Мн. кус момент");
        word33.setLength(3);
        word33.setRarity(Rarity.COMMON);


        Word word34 = new Word();
        word34.setWord("ТЕН");
        word34.setDefinition("Боја на кожата");
        word34.setLength(3);
        word34.setRarity(Rarity.COMMON);


        Word word35 = new Word();
        word35.setWord("ПАН");
        word35.setDefinition("Господин (пол.)");
        word35.setLength(3);
        word35.setRarity(Rarity.COMMON);


        Word word36 = new Word();
        word36.setWord("АТАР");
        word36.setDefinition("Подрачје во град или село");
        word36.setLength(4);
        word36.setRarity(Rarity.COMMON);


        Word word37 = new Word();
        word37.setWord("МИТ");
        word37.setDefinition("Народно предание");
        word37.setLength(3);
        word37.setRarity(Rarity.COMMON);


        Word word38 = new Word();
        word38.setWord("РАМ");
        word38.setDefinition("Компјутерска меморија");
        word38.setLength(3);
        word38.setRarity(Rarity.COMMON);


        Word word39 = new Word();
        word39.setWord("ПАТ");
        word39.setDefinition("Место за движење");
        word39.setLength(3);
        word39.setRarity(Rarity.COMMON);


        Word word40 = new Word();
        word40.setWord("МАТ");
        word40.setDefinition("Последен потег во шахот");
        word40.setLength(3);
        word40.setRarity(Rarity.COMMON);


        Word word41 = new Word();  // НЕ Е ГОТОВО
        word41.setWord("КАРАР");
        word41.setDefinition("Мера, граница");
        word41.setLength(5);
        word41.setRarity(Rarity.COMMON);


        Word word42 = new Word();
        word42.setWord("РОЛЕРИ");
        word42.setDefinition("Чевли со тркалца");
        word42.setLength(6);
        word42.setRarity(Rarity.COMMON);


        Word word43 = new Word();
        word43.setWord("АМАР");
        word43.setDefinition("Комедијантот Си");
        word43.setLength(4);
        word43.setRarity(Rarity.COMMON);


        Word word44 = new Word();
        word44.setWord("К");
        word44.setDefinition("Кенија");
        word44.setLength(1);
        word44.setRarity(Rarity.COMMON);


        Word word45 = new Word();
        word45.setWord("ПАНАГ");
        word45.setDefinition("Јунак");
        word45.setLength(5);
        word45.setRarity(Rarity.COMMON);


        Word word46 = new Word();
        word46.setWord("ЛАТИНИЦА");
        word46.setDefinition("Назив за латинското писмо во сите негови варијанти");
        word46.setLength(8);
        word46.setRarity(Rarity.COMMON);


        Word word47 = new Word();
        word47.setWord("МАНА");
        word47.setDefinition("Недостаток");
        word47.setLength(4);
        word47.setRarity(Rarity.COMMON);


        Word word48 = new Word();
        word48.setWord("КАН");
        word48.setDefinition("Монголски владетел");
        word48.setLength(3);
        word48.setRarity(Rarity.COMMON);


        Word word49 = new Word();
        word49.setWord("А");
        word49.setDefinition("Австрија");
        word49.setLength(1);
        word49.setRarity(Rarity.COMMON);


        Word word50 = new Word();  // не е готово
        word50.setWord("АГАИ");
        word50.setDefinition("Семитска божица на војната");
        word50.setLength(4);
        word50.setRarity(Rarity.COMMON);


        Word word51 = new Word();
        word51.setWord("Л");
        word51.setDefinition("Литар");
        word51.setLength(1);
        word51.setRarity(Rarity.COMMON);


        Word word52 = new Word();
        word52.setWord("РЕК");
        word52.setDefinition("Рударско енергетски комбинат");
        word52.setLength(3);
        word52.setRarity(Rarity.COMMON);


        Word word53 = new Word();
        word53.setWord("АМ");
        word53.setDefinition("Алиша Милано");
        word53.setLength(2);
        word53.setRarity(Rarity.COMMON);


        Word word54 = new Word();  // не е готово
        word54.setWord("ПЛАТА");
        word54.setDefinition("Месечен приход за работа");
        word54.setLength(5);
        word54.setRarity(Rarity.COMMON);


        Word word55 = new Word();
        word55.setWord("КИТ");
        word55.setDefinition("Најголем воден цицач");
        word55.setLength(3);
        word55.setRarity(Rarity.COMMON);


        Word word56 = new Word();
        word56.setWord("НОТА");
        word56.setDefinition("Знак за тон во музиката");
        word56.setLength(4);
        word56.setRarity(Rarity.COMMON);


        Word word57 = new Word();
        word57.setWord("БАР");
        word57.setDefinition("Мал локал");
        word57.setLength(3);
        word57.setRarity(Rarity.COMMON);


        Word word58 = new Word();  // не е готово
        word58.setWord("ХХРАХХН");
        word58.setDefinition("Куса пушка");
        word58.setLength(7);
        word58.setRarity(Rarity.COMMON);


        Word word59 = new Word();
        word59.setWord("АРП");
        word59.setDefinition("Германски сликар Ханс");
        word59.setLength(3);
        word59.setRarity(Rarity.COMMON);


        Word word60 = new Word();
        word60.setWord("ЕМИНА");
        word60.setDefinition("Пејачката Јаховиќ");
        word60.setLength(5);
        word60.setRarity(Rarity.COMMON);


        Word word61 = new Word();
        word61.setWord("НАР");
        word61.setDefinition("Вид калинка");
        word61.setLength(3);
        word61.setRarity(Rarity.COMMON);


        Word word62 = new Word();
        word62.setWord("ФЕН");
        word62.setDefinition("Направа за сушење коса");
        word62.setLength(3);
        word62.setRarity(Rarity.COMMON);


        Word word63 = new Word(); // не е готово
        word63.setWord("ХАХХ");
        word63.setDefinition("Гозба спроти слава");
        word63.setLength(4);
        word63.setRarity(Rarity.COMMON);


        Word word64 = new Word();
        word64.setWord("ДАДА");
        word64.setDefinition("Сестра (нагал.)");
        word64.setLength(4);
        word64.setRarity(Rarity.COMMON);


        Word word65 = new Word();
        word65.setWord("ТЕЛЕФОН");
        word65.setDefinition("Преносник на говор на далечина");
        word65.setLength(7);
        word65.setRarity(Rarity.COMMON);


        Word word66 = new Word();
        word66.setWord("ДА");
        word66.setDefinition("Дарио Аргенто");
        word66.setLength(2);
        word66.setRarity(Rarity.COMMON);


        Word word67 = new Word();
        word67.setWord("КАЗА");
        word67.setDefinition("Управна единица во Отом. Империја");
        word67.setLength(4);
        word67.setRarity(Rarity.COMMON);


        Word word68 = new Word();
        word68.setWord("АР");
        word68.setDefinition("Аксел Роуз");
        word68.setLength(2);
        word68.setRarity(Rarity.COMMON);


        Word word69 = new Word();  // не е готово
        word69.setWord("АМХХИ");
        word69.setDefinition("Писателката Нотномб");
        word69.setLength(5);
        word69.setRarity(Rarity.COMMON);


        Word word70 = new Word();
        word70.setWord("ИКАМЕТ");
        word70.setDefinition("Повик за молитва од минаре");
        word70.setLength(6);
        word70.setRarity(Rarity.COMMON);


        Word word71 = new Word();
        word71.setWord("КАРАТ");
        word71.setDefinition("Мера за чистота на златото");
        word71.setLength(5);
        word71.setRarity(Rarity.COMMON);


        Word word72 = new Word();
        word72.setWord("ШАКА");
        word72.setDefinition("Дланка");
        word72.setLength(4);
        word72.setRarity(Rarity.COMMON);


        Word word73 = new Word();
        word73.setWord("А");
        word73.setDefinition("Азот");
        word73.setLength(1);
        word73.setRarity(Rarity.COMMON);


        Word word74 = new Word();
        word74.setWord("ТИНА");
        word74.setDefinition("Женско име");
        word74.setLength(4);
        word74.setRarity(Rarity.COMMON);


        Word word75 = new Word();
        word75.setWord("РУМ");
        word75.setDefinition("Жесток пијалак");
        word75.setLength(3);
        word75.setRarity(Rarity.COMMON);


        Word word76 = new Word();
        word76.setWord("АДАМ");
        word76.setDefinition("Првиот човек што го создал Бог");
        word76.setLength(4);
        word76.setRarity(Rarity.COMMON);


        Word word77 = new Word();
        word77.setWord("ИРА");
        word77.setDefinition("Штавена овча кожа");
        word77.setLength(3);
        word77.setRarity(Rarity.COMMON);


        Word word78 = new Word();
        word78.setWord("Ш");
        word78.setDefinition("Последната буква");
        word78.setLength(1);
        word78.setRarity(Rarity.COMMON);


        Word word79 = new Word();
        word79.setWord("ЛИН");
        word79.setDefinition("Село во Албанија");
        word79.setLength(3);
        word79.setRarity(Rarity.COMMON);


        Word word80 = new Word();
        word80.setWord("ИРАН");
        word80.setDefinition("Држава во западна Азија");
        word80.setLength(4);
        word80.setRarity(Rarity.COMMON);


        Word word81 = new Word();
        word81.setWord("МЦ");
        word81.setDefinition("Мајкл Цера");
        word81.setLength(2);
        word81.setRarity(Rarity.COMMON);


        Word word82 = new Word();
        word82.setWord("ИЗАХХЛА");
        word82.setDefinition("Покраина на Филипините");
        word82.setLength(7);
        word82.setRarity(Rarity.COMMON);


        Word word83 = new Word();
        word83.setWord("АИ");
        word83.setDefinition("Ана Ивановиќ");
        word83.setLength(2);
        word83.setRarity(Rarity.COMMON);


        Word word84 = new Word();
        word84.setWord("ЦУ");
        word84.setDefinition("Бакар");
        word84.setLength(2);
        word84.setRarity(Rarity.COMMON);


        Word word85 = new Word();
        word85.setWord("АМАРА");
        word85.setDefinition("Пејачката Негра");
        word85.setLength(5);
        word85.setRarity(Rarity.COMMON);


        Word word86 = new Word();
        word86.setWord("И");
        word86.setDefinition("Ирска");
        word86.setLength(1);
        word86.setRarity(Rarity.COMMON);


        Word word87 = new Word();
        word87.setWord("ИЛИК");
        word87.setDefinition("Дупче за копчиња");
        word87.setLength(4);
        word87.setRarity(Rarity.COMMON);


        Word word88 = new Word();
        word88.setWord("А");
        word88.setDefinition("Андора");
        word88.setLength(1);
        word88.setRarity(Rarity.COMMON);


        Word word89 = new Word();
        word89.setWord("АМИ");
        word89.setDefinition("Сврзник, туку");
        word89.setLength(3);
        word89.setRarity(Rarity.COMMON);


        Word word90 = new Word();
        word90.setWord("ЦИА");
        word90.setDefinition("Разузнавачка агенција во САД");
        word90.setLength(3);
        word90.setRarity(Rarity.COMMON);


        Word word91 = new Word();
        word91.setWord("АНИНА");
        word91.setDefinition("Град во Романија");
        word91.setLength(5);
        word91.setRarity(Rarity.COMMON);

        Object[][] positionsAndDirections = {
                {0, 1, ExtensionDirection.VERTICAL}, // x, y, direction
                {0, 2, ExtensionDirection.VERTICAL},
                {0, 3, ExtensionDirection.VERTICAL},
                {0, 4, ExtensionDirection.VERTICAL},
                {0, 5, ExtensionDirection.VERTICAL},
                {0, 6, ExtensionDirection.VERTICAL},
                {0, 8, ExtensionDirection.VERTICAL},
                {0, 9, ExtensionDirection.VERTICAL},
                {0, 10, ExtensionDirection.VERTICAL},
                {0, 11, ExtensionDirection.VERTICAL},
                {0, 12, ExtensionDirection.VERTICAL},
                {0, 13, ExtensionDirection.VERTICAL},
                {1, 0, ExtensionDirection.HORIZONTAL},
                {1, 7, ExtensionDirection.HORIZONTAL},
                {1, 7, ExtensionDirection.VERTICAL},
                {2, 0, ExtensionDirection.HORIZONTAL},
                {2, 3, ExtensionDirection.HORIZONTAL},
                {2, 3, ExtensionDirection.VERTICAL},
                {2, 8, ExtensionDirection.HORIZONTAL},
                {3, 0, ExtensionDirection.HORIZONTAL},
                {3, 4, ExtensionDirection.HORIZONTAL},
                {3, 8, ExtensionDirection.HORIZONTAL},
                {3, 8, ExtensionDirection.VERTICAL},
                {4, 0, ExtensionDirection.HORIZONTAL},
                {4, 4, ExtensionDirection.HORIZONTAL},
                {4, 4, ExtensionDirection.VERTICAL},
                {4, 11, ExtensionDirection.HORIZONTAL},
                {4, 11, ExtensionDirection.VERTICAL},
                {5, 0, ExtensionDirection.HORIZONTAL},
                {5, 2, ExtensionDirection.HORIZONTAL},
                {5, 2, ExtensionDirection.VERTICAL},
                {5, 6, ExtensionDirection.HORIZONTAL},
                {5, 6, ExtensionDirection.VERTICAL},
                {5, 10, ExtensionDirection.HORIZONTAL},
                {5, 10, ExtensionDirection.VERTICAL},
                {6, 0, ExtensionDirection.HORIZONTAL},
                {6, 5, ExtensionDirection.HORIZONTAL},
                {6, 5, ExtensionDirection.VERTICAL},
                {6, 9, ExtensionDirection.HORIZONTAL},
                {6, 9, ExtensionDirection.VERTICAL},
                {6, 13, ExtensionDirection.VERTICAL},
                {7, 0, ExtensionDirection.HORIZONTAL},
                {7, 7, ExtensionDirection.HORIZONTAL},
                {7, 12, ExtensionDirection.HORIZONTAL},
                {8, 1, ExtensionDirection.HORIZONTAL},
                {8, 1, ExtensionDirection.VERTICAL},
                {8, 7, ExtensionDirection.HORIZONTAL},
                {8, 7, ExtensionDirection.VERTICAL},
                {8, 12, ExtensionDirection.HORIZONTAL},
                {8, 12, ExtensionDirection.VERTICAL},
                {9, 0, ExtensionDirection.HORIZONTAL},
                {9, 2, ExtensionDirection.VERTICAL},
                {9, 3, ExtensionDirection.HORIZONTAL},
                {9, 3, ExtensionDirection.VERTICAL},
                {9, 6, ExtensionDirection.HORIZONTAL},
                {9, 6, ExtensionDirection.VERTICAL},
                {9, 10, ExtensionDirection.HORIZONTAL},
                {9, 10, ExtensionDirection.VERTICAL},
                {10, 0, ExtensionDirection.HORIZONTAL},
                {10, 4, ExtensionDirection.VERTICAL},
                {10, 5, ExtensionDirection.HORIZONTAL},
                {10, 5, ExtensionDirection.VERTICAL},
                {10, 9, ExtensionDirection.HORIZONTAL},
                {10, 9, ExtensionDirection.VERTICAL},
                {11, 0, ExtensionDirection.HORIZONTAL},
                {11, 8, ExtensionDirection.HORIZONTAL},
                {11, 8, ExtensionDirection.VERTICAL},
                {11, 11, ExtensionDirection.HORIZONTAL},
                {11, 11, ExtensionDirection.VERTICAL},
                {12, 0, ExtensionDirection.HORIZONTAL},
                {12, 7, ExtensionDirection.HORIZONTAL},
                {12, 13, ExtensionDirection.VERTICAL},
                {13, 0, ExtensionDirection.HORIZONTAL},
                {13, 2, ExtensionDirection.HORIZONTAL},
                {13, 2, ExtensionDirection.VERTICAL},
                {13, 7, ExtensionDirection.HORIZONTAL},
                {13, 7, ExtensionDirection.VERTICAL},
                {13, 12, ExtensionDirection.HORIZONTAL},
                {13, 12, ExtensionDirection.VERTICAL},
                {14, 0, ExtensionDirection.HORIZONTAL},
                {14, 5, ExtensionDirection.VERTICAL},
                {14, 6, ExtensionDirection.HORIZONTAL},
                {14, 6, ExtensionDirection.VERTICAL},
                {15, 0, ExtensionDirection.HORIZONTAL},
                {15, 3, ExtensionDirection.HORIZONTAL},
                {15, 3, ExtensionDirection.VERTICAL},
                {15, 9, ExtensionDirection.HORIZONTAL},
                {15, 9, ExtensionDirection.VERTICAL},
                {16, 0, ExtensionDirection.HORIZONTAL},
                {16, 4, ExtensionDirection.HORIZONTAL},
                {16, 8, ExtensionDirection.HORIZONTAL},
        };

        List<Word> words = new ArrayList<>(Arrays.asList(word1, word2, word3, word4, word5, word6, word7, word8, word9, word10, word11, word12, word13, word14, word15, word16, word17, word18, word19, word20, word21, word22, word23, word24, word25, word26, word27, word28, word29, word30, word31, word32, word33, word34, word35, word36, word37, word38, word39, word40, word41, word42, word43, word44, word45, word46, word47, word48, word49, word50, word51, word52, word53, word54, word55, word56, word57, word58, word59, word60, word61, word62, word63, word64, word65, word66, word67, word68, word69, word70, word71, word72, word73, word74, word75, word76, word77, word78, word79, word80, word81, word82, word83, word84, word85, word86, word87, word88, word89, word90, word91));
        words = wordRepository.saveAll(words);

        List<WordInCrossword> wordInCrosswordList = new ArrayList<>();

        for (int i = 0; i < words.size() && i < positionsAndDirections.length; i++) {
            Word word = words.get(i);
            Object[] params = positionsAndDirections[i];

            WordInCrossword wordInCrossword = new WordInCrossword();
            wordInCrossword.setWord(word);
            wordInCrossword.setCrossword(crossword);
            wordInCrossword.setXPosition((Integer) params[1]);
            wordInCrossword.setYPosition((Integer) params[0]);
            wordInCrossword.setExtensionDirection((ExtensionDirection) params[2]);
            wordInCrossword.setLength(word.getLength());
            wordInCrosswordList.add(wordInCrossword);
        }

        wordInCrosswordList = wordInCrosswordRepository.saveAll(wordInCrosswordList);

    }
}
