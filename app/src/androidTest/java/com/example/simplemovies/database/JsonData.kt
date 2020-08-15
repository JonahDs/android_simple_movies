package com.example.simplemovies.database

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

val jsonGenres =
    """
   {
  "genres": [
    {
      "id": 28,
      "name": "Action"
    },
    {
      "id": 12,
      "name": "Adventure"
    },
    {
      "id": 16,
      "name": "Animation"
    },
    {
      "id": 35,
      "name": "Comedy"
    },
    {
      "id": 80,
      "name": "Crime"
    },
    {
      "id": 99,
      "name": "Documentary"
    },
    {
      "id": 18,
      "name": "Drama"
    },
    {
      "id": 10751,
      "name": "Family"
    },
    {
      "id": 14,
      "name": "Fantasy"
    },
    {
      "id": 36,
      "name": "History"
    },
    {
      "id": 27,
      "name": "Horror"
    },
    {
      "id": 10402,
      "name": "Music"
    },
    {
      "id": 9648,
      "name": "Mystery"
    },
    {
      "id": 10749,
      "name": "Romance"
    },
    {
      "id": 878,
      "name": "Science Fiction"
    },
    {
      "id": 10770,
      "name": "TV Movie"
    },
    {
      "id": 53,
      "name": "Thriller"
    },
    {
      "id": 10752,
      "name": "War"
    },
    {
      "id": 37,
      "name": "Western"
    }
  ]
} 
"""
