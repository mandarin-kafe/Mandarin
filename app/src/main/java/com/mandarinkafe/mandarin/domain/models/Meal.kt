package com.mandarinkafe.mandarin.domain.models

data class Meal(
    val id: String,                // Уникальный идентификатор блюда
    val name: String,              // Название блюда
    val description: String?,      // Описание блюда
    val weight: Int?,              // Вес блюда в граммах
    val price: Int,                // Цена блюда
    val imageUrl: String,          // Ссылка на изображение блюда
    val category: String,          // Категория блюда
    val isVegetarian: Boolean,     // Флаг: является ли блюдо вегетарианским
    val isFavorite: Boolean,     // Флаг: есть ли блюдо в списка избранных (инфо из Shared Prefs)
)

//ff
var mockPizzaList = arrayListOf<Meal>(
    Meal(
        "1",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "2",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "3",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "4",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        "pizza",
        false,
        false
    ),
    Meal(
        "5",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        "pizza",
        false,
        false
    ),
    Meal(
        "1",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        "pizza",
        true,
        true
    ),
    Meal(
        "2",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "3",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "4",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        "pizza",
        false,
        true
    ),
    Meal(
        "5",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        "pizza",
        false,
        false
    ),
    Meal(
        "1",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "2",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        "pizza",
        true,
        false
    ),
    Meal(
        "3",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        "pizza",
        true,
        true
    ),
    Meal(
        "4",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        "pizza",
        false,
        false
    ),
    Meal(
        "5",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        "pizza",
        false,
        false
    ),
)
var mockBurgerList = arrayListOf<Meal>(
    Meal(
        "31",
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
        true
    ),
    Meal(
        "32",
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
        false
    ),
    Meal(
        "33",
        "ДЗЕН",
        "✓ котлета из нута\n" +
                "✓ сыр чеддер\n" +
                "✓ айсберг\n" +
                "✓ красный лук\n" +
                "✓ помидоры\n" +
                "✓ соус сальса\n" +
                "✓ соус максибургер",
        null,
        425,
        "https://optim.tildacdn.com/tild6631-3532-4535-b334-666436313734/-/resize/312x/-/format/webp/_-2.jpg",
        "burger",
        true,
        false
    ),

    )
var mockSushiList = arrayListOf<Meal>(
    Meal(
        "11",
        "КАРМА",
        "Рис, нори, снежный краб, яки соус, огурец, угорь, семга, унаги соус, лайм, икра тобико оранжевая, микрозелень",
        216,
        670,
        "https://optim.tildacdn.com/tild6536-6636-4062-a336-356138393836/-/resize/312x/-/format/webp/karma.jpg",
        "sushi",
        false,
        true
    ),
    Meal(
        "12",
        "СУТРА",
        "Нори, рис, творожный сыр, авокадо, икра тобико оранжевая, семга, шичими, манго соус, майонез",
        210,
        890,
        "https://optim.tildacdn.com/tild3131-3139-4538-b366-656139646633/-/resize/312x/-/format/webp/sutra.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "15",
        "МАНДАРИН",
        "Нори, рис, болгарский перец, жареный лук, огурец, сливочный сыр",
        null,
        310,
        "https://optim.tildacdn.com/tild3935-3437-4462-b535-646633393239/-/resize/312x/-/format/webp/mandarin_veg.jpg",
        "sushi",
        true,
        true

    ),
    Meal(
        "13",
        "ШАМБАЛА",
        "Нори, рис, творожный сыр, омлет, авокадо, манго соус, майонез, семга, сахар коричневый, лайм",
        220,
        810,
        "https://optim.tildacdn.com/tild6330-6464-4736-a662-346566613433/-/resize/312x/-/format/webp/shambala.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "14",
        "ЧАКРА",
        "Нори, рис, маринованный лосось, яки соус, снежный краб, авокадо, огурец",
        225,
        770,
        "https://optim.tildacdn.com/tild3838-3964-4938-b638-343236336364/-/resize/312x/-/format/webp/chakra.jpg",
        "sushi",
        false,
        true
    ),
    Meal(
        "11",
        "КАРМА",
        "Рис, нори, снежный краб, яки соус, огурец, угорь, семга, унаги соус, лайм, икра тобико оранжевая, микрозелень",
        216,
        670,
        "https://optim.tildacdn.com/tild6536-6636-4062-a336-356138393836/-/resize/312x/-/format/webp/karma.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "12",
        "СУТРА",
        "Нори, рис, творожный сыр, авокадо, икра тобико оранжевая, семга, шичими, манго соус, майонез",
        210,
        890,
        "https://optim.tildacdn.com/tild3131-3139-4538-b366-656139646633/-/resize/312x/-/format/webp/sutra.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "15",
        "МАНДАРИН",
        "Нори, рис, болгарский перец, жареный лук, огурец, сливочный сыр",
        null,
        310,
        "https://optim.tildacdn.com/tild3935-3437-4462-b535-646633393239/-/resize/312x/-/format/webp/mandarin_veg.jpg",
        "sushi",
        true,
        true

    ),
    Meal(
        "13",
        "ШАМБАЛА",
        "Нори, рис, творожный сыр, омлет, авокадо, манго соус, майонез, семга, сахар коричневый, лайм",
        220,
        810,
        "https://optim.tildacdn.com/tild6330-6464-4736-a662-346566613433/-/resize/312x/-/format/webp/shambala.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "14",
        "ЧАКРА",
        "Нори, рис, маринованный лосось, яки соус, снежный краб, авокадо, огурец",
        225,
        770,
        "https://optim.tildacdn.com/tild3838-3964-4938-b638-343236336364/-/resize/312x/-/format/webp/chakra.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "11",
        "КАРМА",
        "Рис, нори, снежный краб, яки соус, огурец, угорь, семга, унаги соус, лайм, икра тобико оранжевая, микрозелень",
        216,
        670,
        "https://optim.tildacdn.com/tild6536-6636-4062-a336-356138393836/-/resize/312x/-/format/webp/karma.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "12",
        "СУТРА",
        "Нори, рис, творожный сыр, авокадо, икра тобико оранжевая, семга, шичими, манго соус, майонез",
        210,
        890,
        "https://optim.tildacdn.com/tild3131-3139-4538-b366-656139646633/-/resize/312x/-/format/webp/sutra.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "15",
        "МАНДАРИН",
        "Нори, рис, болгарский перец, жареный лук, огурец, сливочный сыр",
        null,
        310,
        "https://optim.tildacdn.com/tild3935-3437-4462-b535-646633393239/-/resize/312x/-/format/webp/mandarin_veg.jpg",
        "sushi",
        true,
        true

    ),
    Meal(
        "13",
        "ШАМБАЛА",
        "Нори, рис, творожный сыр, омлет, авокадо, манго соус, майонез, семга, сахар коричневый, лайм",
        220,
        810,
        "https://optim.tildacdn.com/tild6330-6464-4736-a662-346566613433/-/resize/312x/-/format/webp/shambala.jpg",
        "sushi",
        false,
        false
    ),
    Meal(
        "14",
        "ЧАКРА",
        "Нори, рис, маринованный лосось, яки соус, снежный краб, авокадо, огурец",
        225,
        770,
        "https://optim.tildacdn.com/tild3838-3964-4938-b638-343236336364/-/resize/312x/-/format/webp/chakra.jpg",
        "sushi",
        false,
        false
    ),

    )