Захарова Сандаара
Б9124-09.03.03 (пикд 3)

Что реализовано в проекте:

1. Экран списка рецептов
- Заголовок приложения "Recipe Book"
- Поле поиска рецептов по названию
- Фильтр по сложности (Все / Легко / Средне / Сложно)
- Список рецептов в виде карточек
- Блок со статистикой (Want to cook / Cooking / Cooked)
- Каждая карточка содержит: название, категорию, время приготовления, сложность, текущее состояние

2. Экран деталей рецепта
- Полная информация: название, описание, ингредиенты, время приготовления, сложность
- Отображение текущего состояния рецепта
- Возможность изменить состояние (три кнопки: Хочу приготовить / Готовлю / Приготовлено)

3. Навигация
- Использование Navigation Compose
- Переход со списка на детали с передачей ID рецепта
- Возврат назад через кнопку "назад" в TopAppBar

4. Управление состоянием (ViewModel)
- ViewModel для экрана списка (RecipeListViewModel)
- ViewModel для экрана деталей (RecipeDetailViewModel)
- Использование StateFlow и UiState
- Composable-функции stateless (получают данные через параметры, события через callbacks)

5. Технические требования
- Полностью офлайн (без сети, без БД)
- Данные хранятся в коде (RecipeRepository)
- Чистая архитектура: отдельные пакеты для data, ui, viewmodel, navigation

<img src="screenshots/photo1.jpg" width="200">  <img src="screenshots/photo2.jpg" width="200">
<img src="screenshots/photo3.jpg" width="200">
<img src="screenshots/photo4.jpg" width="200">
<img src="screenshots/photo5.jpg" width="200">
<img src="screenshots/photo6.jpg" width="200">
<img src="screenshots/photo7.jpg" width="200">
<img src="screenshots/photo8.jpg" width="200">
