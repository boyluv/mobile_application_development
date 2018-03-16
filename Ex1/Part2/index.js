const express = require('express')
const path = require('path')
const PORT = process.env.PORT || 5000

// express()
//   .use(express.static(path.join(__dirname, 'public')))
//   .set('views', path.join(__dirname, 'views'))
//   .set('view engine', 'ejs')
//   .get('/', (req, res) => res.render('pages/index'))
//   .listen(PORT, () => console.log(`Listening on ${ PORT }`))

const app = express()

const getDistance = (lat1,lat2,lng1,lng2) =>{
    const A = 69.1 * (lat2-lat1)
    const B = 69.1 * (lng2 - lng1)* Math.cos(lat1/57.3)
    return Math.sqrt(A*A+B*B)*1609.344
}

app.get('/',
(req, res) => {
        const {lat1,lat2,lng1,lng2} = req.query
        res.setHeader('Content-Type', 'application/json');
        if(lat1 & lat2 & lng1 & lng2)
            res.send(JSON.stringify({ distance: getDistance(lat1,lat2,lng1,lng2) }));
        else
            res.send(JSON.stringify({ error: 'Miss param, example : ' + 'https://pacific-chamber-82484.herokuapp.com/?lat1=10.786471&lat2=10.787907&lng1=106.693645&lng2=106.691903'}));  
        })
        
app.listen(PORT, () => console.log('Example app listening on port 5000!'))  