package com.mandarinkafe.mandarin.domain

data class Meal(
    val id: String,               // Уникальный идентификатор блюда
    val name: String,             // Название блюда
    val description: String,      // Описание блюда
    val weight: Int,              // Вес блюда в граммах
    val price: Int,            // Цена блюда
    val imageUrl: String,         // Ссылка на изображение блюда
    val isVegetarian: Boolean,    // Флаг: является ли блюдо вегетарианским
)

var mockMealkList = arrayListOf<Meal>(
    Meal(
        "1",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        true
    ),
    Meal(
        "2",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        true
    ),
    Meal(
        "3",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        true
    ),
    Meal(
        "4",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        false
    ),
    Meal(
        "5",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        false
    ),
    Meal(
        "1",
        "МАРГАРИТА",
        "Томатный соус, помидоры, моцарелла, орегано и базилик",
        490,
        585,
        "https://optim.tildacdn.com/tild3064-3131-4362-b537-366634323165/-/resize/312x/-/format/webp/margaritta_veg.jpg",
        true
    ),
    Meal(
        "2",
        "МАНДАРИН",
        "Моцарелла, сливочно-чесночный соус, шампиньоны, вяленые томаты, соус песто с грецким орехом, укроп, мягкая моцарелла",
        670,
        985,
        "https://optim.tildacdn.com/tild3666-6364-4133-b961-346132373737/-/resize/312x/-/format/webp/mandarin_new.jpg",
        true
    ),
    Meal(
        "3",
        "ДОЛЬЧЕ ВИТА",
        "Сливочный соус, груша, моцарелла, горгонзола, грецкие орехи, мед",
        650,
        1050,
        "https://optim.tildacdn.com/tild6461-3330-4761-b163-616164303634/-/resize/312x/-/format/webp/dolce_vita_new.jpg",
        true
    ),
    Meal(
        "4",
        "ОХОТНИЧЬЯ",
        "Томатный соус, ветчина, охотничьи колбаски, шампиньоны, маринованные огурцы, моцарелла",
        580,
        770,
        "https://optim.tildacdn.com/tild3830-3031-4662-b131-326163643766/-/resize/312x/-/format/webp/ohotnichiya_new.jpg",
        false
    ),
    Meal(
        "5",
        "МОРЕ ВНУТРИ",
        "Томатный соус, семга, морской окунь, креветки, каперсы, маслины, красный лук, моцарелла, бальзамический крем",
        620,
        1250,
        "https://optim.tildacdn.com/tild6361-6536-4566-b462-643731386235/-/resize/312x/-/format/webp/more_vnutri_new.jpg",
        false
    )
)