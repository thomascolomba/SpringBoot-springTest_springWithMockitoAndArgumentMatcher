Run tests simulating GET request within spring stack.<br/>
This example uses ArgumentMatchers.<br/>
<br/>
compile & test :<br/>
mvn spring-boot:run<br/>
or<br/>
mvn test<br/>

<br/>
--HelloRepositoryImpl.java<br/>
public String returnParam(String myString) {<br/>
&nbsp;&nbsp;return myString;<br/>
<br/>
--HelloServiceImpl.java<br/>
@Autowired<br/>
HelloRepository helloRepository;<br/>
@Override<br/>
public String get(String myString) {<br/>
&nbsp;&nbsp;return helloRepository.returnParam(myString);<br/>
<br/>
--HelloServiceMockTest.java<br/>
<b>@Mock</b>//the mocked class<br/>
private HelloRepository helloRepository;<br/>
<b>@InjectMocks</b>//the class we inject the mocked class within<br/>
private HelloService helloService = new HelloServiceImpl();<br/>
@Test<br/>
void testArgumentMatcherReturnedValueWithMockito() {<br/>
&nbsp;&nbsp;//Arrange<br/>
&nbsp;&nbsp;when(helloRepository.returnParam(<b>ArgumentMatchers.eq("myString1")</b>)).thenReturn("mocked string for parameter myString1");<br/>
&nbsp;&nbsp;when(helloRepository.returnParam(<b>ArgumentMatchers.eq("myString2")</b>)).thenReturn("mocked string for parameter myString2");<br/>
&nbsp;&nbsp;//Act<br/>
&nbsp;&nbsp;String actualValue1 = <b>helloService.get("myString1");</b><br/>
&nbsp;&nbsp;String actualValue2 = <b>helloService.get("myString2");</b><br/>
&nbsp;&nbsp;//Assert<br/>
&nbsp;&nbsp;String expectedValue1 = "mocked string for parameter myString1";<br/>
&nbsp;&nbsp;String expectedValue2 = "mocked string for parameter myString2";<br/>
&nbsp;&nbsp;<b>assertEquals(expectedValue1, actualValue1);<br/>
&nbsp;&nbsp;assertEquals(expectedValue2, actualValue2);</b><br/>
<br/>
@Test<br/>
void testNumberOfCallsWithMockito() {<br/>
&nbsp;&nbsp;//Arrange<br/>
&nbsp;&nbsp;when(helloRepository.returnParam(<b>ArgumentMatchers.eq("myString1")</b>)).thenReturn("mocked string for parameter myString1");<br/>
&nbsp;&nbsp;when(helloRepository.returnParam(<b>ArgumentMatchers.eq("myString2")</b>)).thenReturn("mocked string for parameter myString2");<br/>
&nbsp;&nbsp;//Act<br/>
&nbsp;&nbsp;<b>helloService.get("myString1");<br/>
&nbsp;&nbsp;helloService.get("myString2");<br/>
&nbsp;&nbsp;helloService.get("myString2");</b><br/>
&nbsp;&nbsp;//Assert<br/>
&nbsp;&nbsp;Mockito.verify(helloRepository, <b>Mockito.times(1)</b>).returnParam("myString1");<br/>
&nbsp;&nbsp;Mockito.verify(helloRepository, <b>Mockito.times(2)</b>).returnParam("myString2");<br/>
<br/>