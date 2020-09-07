/*
*
* Useful available asserts:
*   assertEquals("1 should equal 1", 1, 1);
*   assertNotEquals("1 should not equal 2", 1, 2);
*   assertTrue("true should be true", true);
*   assertFalse("false should be false", false);
*   assertNull("null should be null", null);
*   assertNotNull("1 should not be null", 1);
*   assertUndefined("A declared but unassigned variable should have the undefined value", myUndefinedVar);
*   assertNotUndefined("1 should not be undefined", 1);
*   assertNaN("a string should not be a number", "string");
*   assertNotNaN("1 should not be not a number", 1);
*   assertArrayEquals('Equal arrays', [1,2,3], [1,2,3]);
*   assertObjectEquals('Equal objects', {id: 1}, {id: 1});
*
*/

/* example test to check that the header has correct text content */

function testMainHeaderText() {
  let { document } = getTestPage();
  let mainheader = document.querySelector('.mainheader h1');

  assertEquals('Header text should be correct', 'DSP Full Stack Submission', mainheader.textContent);
}

/* example test to check that calling the createTeamsTable method updates the DOM */
/* Note: this will fail because createTeamsTable hasn't yet been implemented in fetchTeams.js */

function testgetTeams() {
  let { window, document } = getTestPage();
  let tableRowsOnLoad = document.querySelectorAll('table tbody tr');

  window.getTeams('');
  // should be five rows including the the headers row for each element
  assertEquals('No. of rows should be 5', 5, tableRowsOnLoad.length);

  // test for specific query string
  window.getTeams('la');

  setTimeout(function () {
    let tableRowsUpdated = document.querySelectorAll('table tbody tr');
    assertEquals('No. of rows should be 2', 2, tableRowsUpdated.length);
  }, 2000)

}

/* continue to add your tests here */

function testgetTeamsEmptyQuery(){
  let { window, document } = getTestPage();
  let tableRowsOnLoad = document.querySelectorAll('table tbody tr');

  console.log('here');

  // should be five rows including the the headers row for each element
  assertEquals('No. of rows should be 5', 5, tableRowsOnLoad.length);

  window.getTeams(' ');

  setTimeout(function () {
    let tableRowsUpdatedEmptyQuery = document.querySelectorAll('table body tr');
    assertEquals('No of rows should be 5', 5, tableRowsUpdatedEmptyQuery.length);
  }, 2000);

}

function testFooterContent(){
  let {document} = getTestPage();
  let footerContent =  document.querySelector('.mainfooter p');

  assertEquals('The text content in the Footer should be correct', 'Date Submitted: 30/07/2019 ', footerContent.textContent);
}

function testHeaderSubmissionContent(){
  let  {document} = getTestPage();
  let submissionName = document.querySelector('.mainheader p');

  assertEquals('The Submission Name is Correct', 'Submission by Ameen Oladehinde-Bello', submissionName.textContent)
}