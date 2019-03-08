Тестировалось только на реальном устройстве с Android 6.0

Для удаления будильника необходимо долгое нажатие на строку в RecyclerView

При рефакторинге liveData в RxJava имеется несколько вариантов реализации обновления UI:

1) В ViewModel храним LiveData, все что приходит из Interactor - это rxJava, который в модели оборачивается в LiveData
2) В ViewModel дополнительно реализуем обертку результата rxJava в новый (Flowable, Observable) rxJava, в данном случае возникает вопрос о необходимости модели
3) В ViewModel просто прокидываем результат rxJava от Interactor - также возникает сомнение в необходимости ViewModel