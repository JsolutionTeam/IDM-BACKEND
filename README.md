# IDM-BACKEND

### 참고사항

- @javax.presistenceColumn
    1. name: 필드명, camelCase를 사용하는 속성의 경우 명시적으로 사용한다.
        - MultiSchemaPhysicalNamingStrategy의 영향으로 필드명 그자체로 데이터베이스에 입력되어 명시한다.

    2. nullable: 속성을 사용하지 않는다. 어플리케이션 단에서 null 처리를 충분히 커버할 수 있다.
