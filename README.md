# Assessment-Test---V2-Technologies-LTD

[![Generic badge](https://img.shields.io/badge/version-1.0.0-<COLOR>.svg)](https://shields.io/)

![alt txt](https://user-images.githubusercontent.com/19292809/88976107-4ecec300-d2dd-11ea-9acf-6cee3485c0f0.png)

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