# Assessment-Test---V2-Technologies-LTD

[![Generic badge](https://img.shields.io/badge/version-1.0.0-<COLOR>.svg)](https://shields.io/)

![alt txt](https://user-images.githubusercontent.com/19292809/88921804-f376e380-d290-11ea-9b80-e3bd870426dd.jpg)


![alt txt](https://user-images.githubusercontent.com/19292809/88921794-f1148980-d290-11ea-9a20-470e1b596970.jpg)


![alt txt](https://user-images.githubusercontent.com/19292809/88921801-f2de4d00-d290-11ea-92c8-334f1a067ba2.jpg)

## Sneak Peeks

Dependency injection with Koin

```java
val dbModule = module {
    single { Room.databaseBuilder(androidContext().applicationContext, AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().surveyDao() }
}
```

Repository pattern

```java
interface SurveyRepository {
    suspend fun fetchNewSurvey(): Survey
    suspend fun storeSurvey(survey: Survey)
    suspend fun getPreviousSurveys(): List<PreviousSurvey>
}
```

Implementing usecases

```java
class FetchSurveyUCImpl(private val surveyRepository: SurveyRepository) : FetchSurveyUC {
    override suspend fun fetch() = surveyRepository.fetchNewSurvey()
}
```

Viewmodels with coroutine

```java
class PreviousSurveyVM(
    private val getPreviousSurveyUC: GetPreviousSurveyUC,
    override val coroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {

    private val _previousSurveyData = MutableLiveData<ModelResponse<List<PreviousSurvey>>> ()
    val prevSurveyData: LiveData<ModelResponse<List<PreviousSurvey>>> = _previousSurveyData

    fun get() {
        launch {
            _previousSurveyData.postValue(Loading())
            try {
                _previousSurveyData.postValue(SuccessResponse(body = getPreviousSurveyUC.get()))
            } catch (e: Exception) {
                _previousSurveyData.postValue(ErrorResponse(errorMessage = e.message))
                e.printStackTrace()
            }
        }
    }
}
```