## Автоматизация мобильных тестов на Android на примере мобильного приложения Wikipedia
Реализован запуск тестов на следующем окружении:
- remote Browserstack device;
- remote selenoid emulator device;
- local emulator device;
- local real smartphone<br/>
### Запуск тестов удаленно
**На [Browserstack](https://www.browserstack.com/)**: 
```
gradle clean browserstack -DdeviceHost=browserstack
```
Состав файла **browserstack.properties**:
```
user=<значение user с Browserstack>
key=<значение key с Browserstack>
url=http://hub.browserstack.com/wd/hub
appUrl=bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c // для Samsung Galaxy S9
device=Samsung Galaxy S9 // для Samsung Galaxy S9
osVersion=8.0 // для Samsung Galaxy S9
project=Android Project // значение для примера
build=build-1 // значение для примера
name=android_tests // значение для примера
```

Также для прохождения теста на авторизацию на Browserstack необходим файл **credentials.properties**, который сформирован в виде Create/Update Text File в конфигурации [Jenkins job](https://jenkins.autotests.cloud/job/08-WakeUpTheo-mobile-Wiki/)


**На эмуляторе сервера selenoid проекта [autotests.cloud](https://selenoid.autotests.cloud/#/)**:
```
gradle clean test -DdeviceHost=selenoid
```
Состав файла **selenoid.properties**:
```
url=https://<username>:<password>@selenoid.autotests.cloud/wd/hub/
deviceName=android
osVersion=8.1
locale=en
language=en
appPackage=org.wikipedia.alpha
appActivity=org.wikipedia.main.MainActivity
appPath=https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk
```


### Запуск тестов локально
**На эмуляторе**: 
```
gradle clean test -DdeviceHost=emulator
```
Состав файла **emulator.properties**:
```
url=http://127.0.0.1:4723/wd/hub
deviceName=Pixel_4_API_30
osVersion=11.0
locale=en
language=en
appPackage=org.wikipedia.alpha
appActivity=org.wikipedia.main.MainActivity
appPath=src/test/resources/app-alpha-universal-release.apk
```


**На смартфоне**:
```
gradle clean test -DdeviceHost=smartphone
```
Состав файла **smartphone.properties**:
```
url=http://127.0.0.1:4723/wd/hub
deviceName=<имя устройства> // в cmd: adb devices
osVersion=<версия Android на устройстве>
locale=en
language=en
appPackage=org.wikipedia.alpha
appActivity=org.wikipedia.main.MainActivity
appPath=src/test/resources/app-alpha-universal-release.apk
```
