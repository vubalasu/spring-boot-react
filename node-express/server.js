const express = require("express");
const cors = require("cors");
const axios = require("axios");

const app = express();

var corsOptions = {
  origin: "*"
};

app.use(cors(corsOptions));

app.use(express.json());

app.use(express.urlencoded({ extended: true }));


// simple route
app.get('/countries', (req, res) => {
  axios.get('https://restcountries.com/v2/regionalbloc/eu?fields=name,alpha2Code') // API url which is getting data
      .then((response) => {
        res.send(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
});

// set port, listen for requests
const PORT = process.env.PORT || 3003;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});
