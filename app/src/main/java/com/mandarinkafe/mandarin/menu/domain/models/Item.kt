package com.mandarinkafe.mandarin.menu.domain.models

import com.mandarinkafe.mandarin.menu.data.dto.Tag

data class Item(
    val id: String,
    val sku: String,
    val name: String,
    val description: String?,
    val weight: Int?,
    val price: Int,
    val imageUrl: String?,
    var categoryId: String?,
    var isFavorite: Boolean,
    val tags: List<Tag>?,
)


var mockPizzaList = arrayListOf<Item>(
    Item(
        "1",
        "0013",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        "pizza",
        true,
        null,
        ),
    Item(
        "2",
        "0013",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "3",
        "0013",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        "pizza",
        true,
        null
    ),
    Item(
        "4",
        "0013",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "5",
        "0013",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "1",
        "0013",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        "pizza",
        true,
        null
    ),
    Item(
        "2",
        "0013",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        "pizza",
        true,
        null
    ),
    Item(
        "3",
        "0013",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "4",
        "0013",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "5",
        "0013",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "1",
        "0013",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        "pizza",
        true,
        null
    ),
    Item(
        "2",
        "0013",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        "pizza",
        true,
        null
    ),
    Item(
        "3", "0013",

        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        "pizza",
        true,
        null
    ),
    Item(
        "4",
        "0013",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        "pizza",
        false,
        null
    ),
    Item(
        "5", "0013",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        "pizza",
        false,
        null
    ),
)
var mockBurgerList = arrayListOf<Item>(
    Item(
        "31", "0013",
        "ДАКОТА",
        "✓ говяжья котлета\n" +
                "✓ сыр чеддер\n" +
                "✓ бекон\n" +
                "✓ лук фри\n" +
                "✓ маринованные огурцы\n" +
                "✓ помидоры\n" +
                "✓ соус сальса\n" +
                "✓ соус тартар",
        null,
        595,
        "https://optim.tildacdn.com/tild3763-6634-4137-a662-383034323938/-/resize/312x/-/format/webp/_-2.jpg",
        "burger",
        false,
        null
    ),
    Item(
        "32", "0013",
        "ХАМБУРГЕР",
        "✓ булка\n" +
                "✓ говяжья котлета\n" +
                "✓ сыр чеддер\n" +
                "✓ соус микс барбекю",
        null,
        455,
        "https://optim.tildacdn.com/tild6337-3237-4264-a535-333266346235/-/resize/312x/-/format/webp/_-2.jpg",
        "burger",
        false,
        null
    ),
    Item(
        "33", "0013",
        "ДЗЕН",
        "✓ котлета из нута\n" +
                "✓ сыр чеддер\n" +
                "✓ айсберг\n" +
                "✓ красный лук\n" +
                "✓ помидоры\n" +
                "✓ соус сальса\n" +
                "✓ соус максибургер",
        300,
        425,
        "https://optim.tildacdn.com/tild6631-3532-4535-b334-666436313734/-/resize/312x/-/format/webp/_-2.jpg",
        "burger",
        true,
        null
    ),
    Item(
        "32", "0013",
        "ХАМБУРГЕР",
        "✓ булка\n" +
                "✓ говяжья котлета\n" +
                "✓ сыр чеддер\n" +
                "✓ соус микс барбекю",
        299,
        455,
        "https://optim.tildacdn.com/tild6337-3237-4264-a535-333266346235/-/resize/312x/-/format/webp/_-2.jpg",
        "burger",
        false,
        null
    ),
    Item(
        "33", "0013",
        "ДЗЕН",
        "✓ котлета из нута\n" +
                "✓ сыр чеддер\n" +
                "✓ айсберг\n" +
                "✓ красный лук\n" +
                "✓ помидоры\n" +
                "✓ соус сальса\n" +
                "✓ соус максибургер",
        300,
        425,
        "https://optim.tildacdn.com/tild6631-3532-4535-b334-666436313734/-/resize/312x/-/format/webp/_-2.jpg",
        "burger",
        true,
        null
    ),

    )
var mockSushiList = arrayListOf<Item>(
    Item(
        "11", "0013",
        "КАРМА",
        "Рис, нори, снежный краб, яки соус, огурец, угорь, семга, унаги соус, лайм, икра тобико оранжевая, микрозелень",
        216,
        670,
        "https://optim.tildacdn.com/tild6536-6636-4062-a336-356138393836/-/resize/312x/-/format/webp/karma.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "12", "0013",
        "СУТРА",
        "Нори, рис, творожный сыр, авокадо, икра тобико оранжевая, семга, шичими, манго соус, майонез",
        210,
        890,
        "https://optim.tildacdn.com/tild3131-3139-4538-b366-656139646633/-/resize/312x/-/format/webp/sutra.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "15", "0013",
        "МАНДАРИН",
        "Нори, рис, болгарский перец, жареный лук, огурец, сливочный сыр",
        null,
        310,
        "https://optim.tildacdn.com/tild3935-3437-4462-b535-646633393239/-/resize/312x/-/format/webp/mandarin_veg.jpg",
        "sushi",
        true,
        null

    ),
    Item(
        "13", "0013",
        "ШАМБАЛА",
        "Нори, рис, творожный сыр, омлет, авокадо, манго соус, майонез, семга, сахар коричневый, лайм",
        220,
        810,
        "https://optim.tildacdn.com/tild6330-6464-4736-a662-346566613433/-/resize/312x/-/format/webp/shambala.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "14", "0013",
        "ЧАКРА",
        "Нори, рис, маринованный лосось, яки соус, снежный краб, авокадо, огурец",
        225,
        770,
        "https://optim.tildacdn.com/tild3838-3964-4938-b638-343236336364/-/resize/312x/-/format/webp/chakra.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "11", "0013",
        "КАРМА",
        "Рис, нори, снежный краб, яки соус, огурец, угорь, семга, унаги соус, лайм, икра тобико оранжевая, микрозелень",
        216,
        670,
        "https://optim.tildacdn.com/tild6536-6636-4062-a336-356138393836/-/resize/312x/-/format/webp/karma.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "12", "0013",
        "СУТРА",
        "Нори, рис, творожный сыр, авокадо, икра тобико оранжевая, семга, шичими, манго соус, майонез",
        210,
        890,
        "https://optim.tildacdn.com/tild3131-3139-4538-b366-656139646633/-/resize/312x/-/format/webp/sutra.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "15", "0013",
        "МАНДАРИН",
        "Нори, рис, болгарский перец, жареный лук, огурец, сливочный сыр",
        null,
        310,
        "https://optim.tildacdn.com/tild3935-3437-4462-b535-646633393239/-/resize/312x/-/format/webp/mandarin_veg.jpg",
        "sushi",
        true,
        null

    ),
    Item(
        "13", "0013",
        "ШАМБАЛА",
        "Нори, рис, творожный сыр, омлет, авокадо, манго соус, майонез, семга, сахар коричневый, лайм",
        220,
        810,
        "https://optim.tildacdn.com/tild6330-6464-4736-a662-346566613433/-/resize/312x/-/format/webp/shambala.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "14", "0013",
        "ЧАКРА",
        "Нори, рис, маринованный лосось, яки соус, снежный краб, авокадо, огурец",
        225,
        770,
        "https://optim.tildacdn.com/tild3838-3964-4938-b638-343236336364/-/resize/312x/-/format/webp/chakra.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "11", "0013",
        "КАРМА",
        "Рис, нори, снежный краб, яки соус, огурец, угорь, семга, унаги соус, лайм, икра тобико оранжевая, микрозелень",
        216,
        670,
        "https://optim.tildacdn.com/tild6536-6636-4062-a336-356138393836/-/resize/312x/-/format/webp/karma.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "12", "0013",
        "СУТРА",
        "Нори, рис, творожный сыр, авокадо, икра тобико оранжевая, семга, шичими, манго соус, майонез",
        210,
        890,
        "https://optim.tildacdn.com/tild3131-3139-4538-b366-656139646633/-/resize/312x/-/format/webp/sutra.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "15", "0013",
        "МАНДАРИН",
        "Нори, рис, болгарский перец, жареный лук, огурец, сливочный сыр",
        null,
        310,
        "https://optim.tildacdn.com/tild3935-3437-4462-b535-646633393239/-/resize/312x/-/format/webp/mandarin_veg.jpg",
        "sushi",
        true,
        null

    ),
    Item(
        "13", "0013",
        "ШАМБАЛА",
        "Нори, рис, творожный сыр, омлет, авокадо, манго соус, майонез, семга, сахар коричневый, лайм",
        220,
        810,
        "https://optim.tildacdn.com/tild6330-6464-4736-a662-346566613433/-/resize/312x/-/format/webp/shambala.jpg",
        "sushi",
        false,
        null
    ),
    Item(
        "14", "0013",
        "ЧАКРА",
        "Нори, рис, маринованный лосось, яки соус, снежный краб, авокадо, огурец",
        225,
        770,
        "https://optim.tildacdn.com/tild3838-3964-4938-b638-343236336364/-/resize/312x/-/format/webp/chakra.jpg",
        "sushi",
        false,
        null
    ),

    )
var mockHotDogList = arrayListOf<Item>(
    Item(
        "41", "0013",
        "ДАТСКИЙ ХОТ-ДОГ",
        "✓ сосиска на выбор\n" +
                "✓ булка\n" +
                "✓ огурцы\n" +
                "✓ лук фри\n" +
                "✓ горчица\n" +
                "✓ кетчуп\n" +
                "✓ майонезный соус",
        null,
        250,
        "https://optim.tildacdn.com/tild3866-3065-4137-b633-616633396165/-/resize/312x/-/format/webp/datsky_hot-dog_new.jpg",
        "hotdog",
        false,
        null
    ),
    Item(
        "42", "0013",
        "ФРАНЦУЗСКИЙ ХОТ-ДОГ",
        "✓ сосиска на выбор\n" +
                "✓ майонезный соус\n" +
                "✓ кетчуп\n" +
                "✓ булка",
        null,
        250,
        "https://optim.tildacdn.com/tild3361-3662-4537-b731-393636653530/-/resize/312x/-/format/webp/francuzsky_hot-dog_n.jpg",
        "hotdog",
        false,
        null
    ),
    Item(
        "43", "0013",
        "ДОНЕР",
        "✓ пшеничная тортилья\n" +
                "✓ мясная начинка или фалафель по выбору\n" +
                "✓ картофель фри\n" +
                "✓ огурцы\n" +
                "✓ помидоры\n" +
                "✓ соус тартар\n" +
                "✓ сальса\n" +
                "✓ сыр моцарелла",
        null,
        390,
        "https://optim.tildacdn.com/tild6261-3063-4939-a162-366337393735/-/resize/312x/-/format/webp/doner_new.jpg",
        "hotdog",
        false,
        null
    ),
)

var mockWokList = arrayListOf<Item>(
    Item(
        "51", "0013",
        "Пшеничная лапша с овощами, курицей, черри, пармезаном в сливочном соусе",
        "Пшеничная лапша, цукини, морковь, перец болгарский, лук, курица, помидоры черри, пармезан, кунжутное масло, сливочный соус, кунжут",
        null,
        450,
        "https://optim.tildacdn.com/tild3130-6233-4163-a638-373037373032/-/resize/312x/-/format/webp/pshenichnaya_s_kuric.jpg",
        "wok",
        false,
        null
    ),
    Item(
        "52", "0013",
        "ЖАРЕНЫЙ РИС С ЯЙЦОМ И НАЧИНКОЙ НА ВЫБОР",
        "Рис, курица / свинина / креветки (на выбор), морковь, чеснок, яйцо, лук порей, кунжутное масло, кунжут, устричный соус, пикантный соус",
        null,
        250,
        "https://optim.tildacdn.com/tild3263-3037-4137-a634-666663333863/-/resize/312x/-/format/webp/ris_s_krevetkami_new.jpg",
        "wok",
        false,
        null
    ),
    Item(
        "53", "0013",
        "CОБЕРИ СВОЮ КОРОБОЧКУ",
        "Шаг 1. Выбери основу (в комплекте овощи и соус. Все вместе — 330 г)\n" +
                "Шаг 2. Добавь мясо, начинку, дополнительный соус по своему вкусу!",
        null,
        0,
        "https://optim.tildacdn.com/tild3866-3065-4137-b633-616633396165/-/resize/312x/-/format/webp/datsky_hot-dog_new.jpg",
        "wok",
        false,
        null
    ),
)


var mockAdditionalsList = arrayListOf<Item>(
    Item(
        "99", "0013",
        "Пармезан",
        "",
        60,
        120,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Моцарелла",
        "",
        70,
        90,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Салями",
        "",
        50,
        120,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Бекон",
        "",
        60,
        110,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Моцарелла",
        "",
        70,
        90,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Салями",
        "",
        50,
        120,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Мягкая моцарелла",
        "",
        70,
        90,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null,
    ),
    Item(
        "98", "0013",
        "Салями",
        "",
        50,
        120,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "99", "0013",
        "Пармезан",
        "",
        60,
        120,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Мягкая моцарелла",
        "",
        70,
        90,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Охотничьи колбаски",
        "",
        50,
        120,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Бекон",
        "",
        60,
        110,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Моцарелла",
        "",
        70,
        90,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Салями",
        "",
        50,
        120,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
    Item(
        "98", "0013",
        "Моцарелла",
        "",
        70,
        90,
        "https://optim.tildacdn.com/tild6462-6661-4039-b165-353734396636/-/format/webp/dobavki_syr.png",
        "additionals",
        false,
        null,
    ),
    Item(
        "98", "0013",
        "Охотничьи колбаски",
        "",
        50,
        120,
        "https://optim.tildacdn.com/tild3932-3938-4236-b732-626136386461/-/format/webp/myaso_new.png",
        "additionals",
        false,
        null
    ),
)