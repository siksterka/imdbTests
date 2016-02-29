Feature: Check top250 list on imdb website

  Scenario: Check that default top250 list has results
    When user goes to top250 imdb page
      Then there is at least one result on page

  Scenario: Check sorting options for top250 list
    When user goes to top250 imdb page
      And user sorts by "Ranking"
        Then there is at least one result on page
      When user reverts order of sorting
        Then there is at least one result on page

      When user sorts by "IMDb Rating"
        Then there is at least one result on page
      When user reverts order of sorting
        Then there is at least one result on page

      When user sorts by "Release Date"
        Then there is at least one result on page
      When user reverts order of sorting
        Then there is at least one result on page

      When user sorts by "Number of Ratings"
        Then there is at least one result on page
      When user reverts order of sorting
        Then there is at least one result on page

      When user sorts by "Your Rating"
        Then there is at least one result on page
      When user reverts order of sorting
        Then there is at least one result on page

    Scenario: Check results for genre list
      When user goes to top250 imdb page
        And clicks on "Western" genre
          Then there is at least one result on page