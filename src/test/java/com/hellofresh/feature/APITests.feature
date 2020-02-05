@API
Feature: Automated Rest Api Tests

  Scenario Outline: User is creating new booking
    Given I want to set URL as "<Url>"
    When I set header content type as "<ContentType>"
    When I hit the API with requestbody "<RequestBody>" having request method is "<RequestMethod>" and parameter is "<Parameter>" and filetype "<FileType>"
    Then I try to verify the status code is "<StatusCode>"

    Examples: 
      | Url       | ContentType      | RequestBody                       | Parameter | RequestMethod | FileType | StatusCode |
      | /booking/ | application/json | createbooking_successrequest.json | NA        | POST          | JSON     |        201 |

  Scenario Outline: User is creating new booking with existing details
    Given I want to set URL as "<Url>"
    When I set header content type as "<ContentType>"
    When I hit the API with requestbody "<RequestBody>" having request method is "<RequestMethod>" and parameter is "<Parameter>" and filetype "<FileType>"
    Then I try to verify the status code is "<StatusCode>"

    Examples: 
      | Url       | ContentType      | RequestBody                                    | Parameter | RequestMethod | FileType | StatusCode |
      | /booking/ | application/json | createbooking_successrequest_duplicate.json    | NA        | POST          | JSON     |        409 |
      | /booking/ | application/json | createbooking_checkoutdatelessthancheckin.json | NA        | POST          | JSON     |        409 |
      | /booking/ | application/json | createbooking_checkoutdatemissing.json         | NA        | POST          | JSON     |        400 |

  Scenario Outline: User is fetching new bookings
    Given I want to set URL as "<Url>"
    When I set header content type as "<ContentType>"
    When I set param roomid as "<Parameter>"
    When I hit the API with requestbody "<RequestBody>" having request method is "<RequestMethod>" and parameter is "<Parameter>" and filetype "<FileType>"
    Then I try to verify the status code is "<StatusCode>"
    Then I try to verify the number of bookings is "<Count>"

    Examples: 
      | Url       | ContentType      | RequestBody | Parameter | RequestMethod | FileType | StatusCode | Count |
      | /booking/ | application/json | {}          |         1 | GET           | NA       |        200 |     2 |
      | /booking/ | application/json | {}          |         2 | GET           | NA       |        200 |     1 |

  Scenario Outline: User is fetching only one booking
    Given I want to set URL as "<Url>"
    When I set header content type as "<ContentType>"
    When I hit the API with requestbody "<RequestBody>" having request method is "<RequestMethod>" and parameter is "<Parameter>" and filetype "<FileType>"
    Then I try to verify the status code is "<StatusCode>"
    Then I try to verify the number of booking is "<Count>" for "<Key>"

    Examples: 
      | Url        | ContentType      | RequestBody | Parameter | RequestMethod | FileType | StatusCode | Key       | Count |
      | /booking/1 | application/json | {}          | NA        | GET           | NA       |        200 | bookingid |     1 |
      | /booking/2 | application/json | {}          | NA        | GET           | NA       |        200 | bookingid |     1 |
