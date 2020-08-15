package com.example.simplemovies.mockdata

val jsonMovie =
    """
    {
  "page": 1,
  "total_results": 10000,
  "total_pages": 500,
  "results": [
    {
      "popularity": 269.003,
      "vote_count": 2430,
      "video": false,
      "poster_path": "/mb7wQv0adK3kjOUr9n93mANHhPJ.jpg",
      "id": 583083,
      "adult": false,
      "backdrop_path": "/wO5QSWZPBT71gMLvrRex0bVc0V9.jpg",
      "original_language": "en",
      "original_title": "The Kissing Booth 2",
      "genre_ids": [
        35,
        10749
      ],
      "title": "The Kissing Booth 2",
      "vote_average": 8.2,
      "overview": "With college decisions looming, Elle juggles her long-distance romance with Noah, changing relationship with bestie Lee and feelings for a new classmate.",
      "release_date": "2020-07-24"
    },
    {
      "popularity": 172.535,
      "id": 300671,
      "video": false,
      "vote_count": 2039,
      "vote_average": 7.1,
      "title": "13 Hours: The Secret Soldiers of Benghazi",
      "release_date": "2016-01-13",
      "original_language": "en",
      "original_title": "13 Hours: The Secret Soldiers of Benghazi",
      "genre_ids": [
        28,
        36,
        53
      ],
      "backdrop_path": "/ayDMYGUNVvXS76wQgFwTiUIDNb5.jpg",
      "adult": false,
      "overview": "An American Ambassador is killed during an attack at a U.S. compound in Libya as a security team struggles to make sense out of the chaos.",
      "poster_path": "/4qnEeVPM8Yn5dIVC4k4yyjrUXeR.jpg"
    }]
}
"""

val jsonCast =
    """
   {
  "id": 583083,
  "cast": [
    {
      "cast_id": 1,
      "character": "Elle Evans",
      "credit_id": "5f1abfe62afb25003414b13e",
      "gender": 1,
      "id": 125025,
      "name": "Joey King",
      "order": 0,
      "profile_path": "/jUuKlP1hjPJ6R0d6MhUytr85oRL.jpg"
    },
    {
      "cast_id": 2,
      "character": "Noah Flynn",
      "credit_id": "5f1abff3303c85003346868a",
      "gender": 2,
      "id": 2034418,
      "name": "Jacob Elordi",
      "order": 1,
      "profile_path": "/pW1zD9yHazjiZ8yLuYY50ruFymu.jpg"
    },
    {
      "cast_id": 3,
      "character": "Lee Flynn",
      "credit_id": "5f1ac029691cd50035956113",
      "gender": 2,
      "id": 518627,
      "name": "Joel Courtney",
      "order": 2,
      "profile_path": "/2PSvG7Qd9dUMFonifSZCvDnDBrL.jpg"
    },
    {
      "cast_id": 4,
      "character": "Mrs. Flynn",
      "credit_id": "5f1b1907956658003642114e",
      "gender": 1,
      "id": 21625,
      "name": "Molly Ringwald",
      "order": 3,
      "profile_path": "/i9NTMtw5Ydukz0Lu5r7VBuZ7dOj.jpg"
    },
    {
      "cast_id": 5,
      "character": "Marco Peña",
      "credit_id": "5f1b191fa88587003661d018",
      "gender": 2,
      "id": 2648891,
      "name": "Taylor Zakhar Perez",
      "order": 4,
      "profile_path": null
    },
    {
      "cast_id": 6,
      "character": "Chloe Winthrop",
      "credit_id": "5f1b19327aecc600351c7549",
      "gender": 1,
      "id": 1399526,
      "name": "Maisie Richardson-Sellers",
      "order": 5,
      "profile_path": "/sHCrVtjwhJ7TtvPgSVbirjwD3RS.jpg"
    },
    {
      "cast_id": 7,
      "character": "Rachel",
      "credit_id": "5f1b1982956658003441db5b",
      "gender": 1,
      "id": 1412941,
      "name": "Meganne Young",
      "order": 6,
      "profile_path": "/r4ONYPCjDw74mJykgmagnnLPCOc.jpg"
    }]}
"""
