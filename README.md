# EGV-ERP-final-project
> 영화관 사업 특화 ERP 시스템


## ℹ️개요
ERP(Enterprise Resource Planning)란 전사적 자원관리로 회사의 재무, 공급망, 운영, 보고, 인적자원 활동 등 비즈니스 프로세스를 통합 관리하는 소프트웨어입니다. ERP를 도입하지 않은 대부분의 회사는 일정 정도의 재무 및 운영 시스템을 갖추고 있지만 사용하고 있는 대부분의 소프트웨어가 일상적인 업무 프로세스 외에 다른 기능을 지원해주지 못해 미래의 비즈니스 성장에도 도움을 받지 못합니다.  
EGV는 기본적인 ERP의 기능들 뿐만 아니라 영화관 운영에 필요한 기능들을 추가로 구현한 영화관 사업 특화 ERP입니다. 개봉 예정 영화들의 정보를 조회해 스케줄링하고, 데일리로 현재 상영중인 영화 스케줄을 작성할 수 있습니다. 더하여 영화관에서 진행하는 이벤트 또한 관리 할 수 있습니다.


## 👏Contributors
- 김주호([kjh00345](https://github.com/kjh00345)) 📅`일정관리`
- 김준우([junwo0](https://github.com/junwo0)) 🤯`이슈관리`
- 김지원([1215wldnjs](https://github.com/1215wldnjs)) 📊`DB관리`
- 김한솔([businesshskim](https://github.com/businesshskim)) 📜`문서(노션)관리`
- 송언석([darkasassin44](https://github.com/darkasassin44)) ⌚️`형상관리`
- 이선민([smksm08](https://github.com/smksm08)) 👨‍🔬`테스트관리`
- 이한수([orca-ss](https://github.com/orca-ss)) 🗄️`업무관리`


## 🛠개발환경
### OS
- Windows 10 64bit
- macOS Monterey

### Server
- Oracle Cloud
  - 컴퓨트 인스턴스
  - 자율운영 트랜잭션 처리 데이터베이스
- Apache Tomcat 9

### Language
- Java OpenJDK 1.8
- Oracle Database 19c
- HTML5
- CSS3
- JavaScript

### Framework
- Spring Framework
  - Spring Boot `v2.5.7`
  - Spring Data JPA `v2.5.7`
  - Spring Security `v2.5.7`
  - Thymeleaf `v2.5.7`
- ojdbc8

### Tools
- Spring Tools 4 `v4.12.1.RELEASE`
- Oracle SQL Developer `v21.2.1`

### Version Control System
- Git
- GitHub
- GitKraken

### Open Source Libraries
- [Bootstrap](https://getbootstrap.com/)
- [DataTables](https://datatables.net/)
- [FullCalendar](https://fullcalendar.io/)
- [jQuery](https://jquery.com/)
- [summernote](https://summernote.org/)

### Prototyping
🎨[Figma](https://www.figma.com/file/TgDh6KCUDlAPbqKHxMiEk4/EGV-ERP-Project?node-id=0%3A1)

### Entity Relationship Model
🔗![ERDCloud](https://github.com/final-EGV/EGV-ERP-final-project/blob/master/docs/images/EGV_ERD_overall_screenshot.png)


## ✨주요 기능
> ❗ 해당 브랜치에서는 **공통 기능, 영화관 관리, 로깅 기능의 일부만**을 다룹니다.
- **공통 기능**
- 인사 관리
- **영화관 관리**
- 전자결재
- 커뮤니케이션

### 사용법
아래 서버 주소로 접속하여 알맞는 계정으로 로그인한 후 사용해주시면 됩니다.  
> Oracle Cloud 접속 주소 : [http://152.70.234.22:8887](http://152.70.234.22:8887)

### 공통 기능
> 공통 기능을 확인하시려면 다음 샘플 계정을 사용하여 로그인해주시기 바랍니다. 아래 계정에만 샘플 데이터가 준비되어 있습니다.  
> - 사번(ID) : 2021100  
> - 비밀번호 : pass01  

![메인화면](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EA%B3%B5%ED%86%B5%EA%B8%B0%EB%8A%A5_%EB%A9%94%EC%9D%B8%ED%99%94%EB%A9%B4_1.png)

#### 메인 페이지에서 위젯 형식으로 개인 일정과 영화관의 일정을 모두 확인 가능
- 나의 일정
- 전자결재
- 연차 현황
- 이벤트 일정
- 상영 스케줄

#### 애플리케이션의 주요 디자인과 통일된 사용자 지정 에러 페이지
| 404 error                                                                                                                                                                                                                                                                     | 500 error                                                                                                                                                                                                                                                                     |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![404 error](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EA%B3%B5%ED%86%B5%EA%B8%B0%EB%8A%A5_%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC_%EC%82%AC%EC%9A%A9%EC%9E%90%EC%A7%80%EC%A0%95%EC%97%90%EB%9F%AC%ED%99%94%EB%A9%B4_1.gif) | ![500 error](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EA%B3%B5%ED%86%B5%EA%B8%B0%EB%8A%A5_%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC_%EC%82%AC%EC%9A%A9%EC%9E%90%EC%A7%80%EC%A0%95%EC%97%90%EB%9F%AC%ED%99%94%EB%A9%B4_2.gif) |

### 영화관 관리
> 영화관 관리 기능을 확인하시려면 다음 샘플 계정을 사용하여 로그인해주시기 바랍니다. 아래 계정에만 영화관 관리 페이지에 대한 접근 권한이 부여되어 있습니다.  
> - 사번(ID) : 2021105  
> - 비밀번호 : pass01  

#### 빠른 정렬, 검색 속도
| 빠른 정렬                                                                                                                                                                                                              | 빠른 검색                                                                                                                                                                                                              |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![빠른정렬](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EC%98%81%ED%99%94_%EA%B8%B0%EB%8A%A5%EA%B5%AC%ED%98%84_1.gif) | ![빠른검색](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EC%98%81%ED%99%94_%EA%B8%B0%EB%8A%A5%EA%B5%AC%ED%98%84_2.gif) |

#### 현재 시간에 맞춰 자동으로 상영 스케줄을 조회
![상영 스케줄 조회](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EC%83%81%EC%98%81%EC%8A%A4%EC%BC%80%EC%A4%84_%EB%A9%94%EC%9D%B8%ED%99%94%EB%A9%B4_1.png)

#### 영화 러닝타임을 상영 종료 시간에 반영하여 자동 계산
| 상영 스케줄 등록시                                                                                                                                                                                                                                        | 상영 스케줄 수정시                                                                                                                                                                                                                                        |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![상영 스케줄 등록](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EC%83%81%EC%98%81%EC%8A%A4%EC%BC%80%EC%A4%84_%EA%B8%B0%EB%8A%A5%EA%B5%AC%ED%98%84_1.gif) | ![상영 스케줄 수정](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EC%83%81%EC%98%81%EC%8A%A4%EC%BC%80%EC%A4%84_%EA%B8%B0%EB%8A%A5%EA%B5%AC%ED%98%84_2.gif) |

#### 서버 로깅 기능
> 영화관 관리 기능에 한하여 서버 로깅 기능이 구현되어 있습니다. 즉, `/org/erp/egv/theater` 이하 패키지에 속한 bean들에 한하여 로그를 기록합니다.
- Spring AOP 기술을 사용해 부가 기능인 로깅 기능을 주요 기능에서 분리
- 요청 및 응답에 대한 정보, MVC 패턴 상에서 메소드의 호출과 종료를 기록

![로깅 다이어그램](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EB%A1%9C%EA%B9%85_%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png)
![실제 서버 로깅](https://github.com/final-EGV/EGV-ERP-final-project/blob/unsuk-oracle-cloud-deploy/docs/images/%EC%98%81%ED%99%94%EA%B4%80%EA%B4%80%EB%A6%AC_%EB%A1%9C%EA%B9%85_%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7.png)

## ✔️Git Commit Message Convention
### 기본 포맷
```
# 제목
<타입>: 해당 커밋에 대한 간략한 설명(최대 72자)
########## 제목-본문 구분 공백 ##########
#본문
- 자세한 설명.
- 혹은 부연 설명.
```
### 타입(Type)
- **Feat**: 새로운 기능 추가
- **Fix**: 버그 수정
- **Refactor**: 코드 리펙토링
- **Style**: 포맷팅, 오탈자 등 코드 의미에 변화는 없을 때
- **Docs**: 문서(Docs) 수정
- **Comment**: 주석(comment) 수정
- **Rename**: 파일, 폴더, 패키지 등의 이름 변경
- **Remove**: 파일, 폴더, 패키지 등을 삭제
- **Add**: 파일, 폴더, 패키지 등을 추가
- **Test**: 새로운 테스트 추가, 혹은 테스트 리펙토링(코드 의미에 변화 없음)
- **Chore**: 빌드 혹은 패키지 매니저 수정사항(코드 의미에 변화 없음)

### 제목
- 제목의 첫 글자는 대문자로 작성할 것
- 제목은 현재시제 명령문으로 작성할 것
- 제목 마지막에 마침표를 사용하지 말것

### 본문
- 제목과 본문은 빈 공백을 사용하여 구분할 것
- 본문에서는 '무엇'을 '어떻게' 보다는 '**왜**'라는 이유에 무게를 실어 설명할 것
- 본문에서 여러 줄을 작성할 때는 `-`을 사용해 작성할 것

### 커밋 메시지 템플릿
- [한국어.ver](https://github.com/final-EGV/EGV-ERP-final-project/blob/master/docs/.gitmessageKor.txt)
- [영어.ver](https://github.com/final-EGV/EGV-ERP-final-project/blob/master/docs/.gitmessageEng.txt)

### 템플릿 적용하기
1. 템플릿 파일을 git 관리하에 있는(`.git` 이 존재하는) 디렉토리에 저장한다.
2. native git의 --global 레벨의 설정 파일에 작성한 템플릿을 commit template로 적용한다.
```sh
// 한글 템플릿을 사용하는 경우
git config --global commit.template .gitmessageKor.txt

// 영어 템플리을 사용하는 경우
git config --global commit.template .gitmessageEng.txt
```
3. `git commit` 명령어로 커밋하면 해당 템플릿으로 커밋 메시지가 작성된다.