/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// constructor
var translator = new Translator();


// voiceToText method allows you convert voice to text; 
// whatever you speak is recognized using Google 
// Speech-Recognition API; and converted into 
// text using same API:
var data = "";
var worked = 0;
var counterImage = 1;
var searchedName;
var i = 0;
var z = 0;
var pos = 0;
var ypos = 0;
var speed;
var setsOfData = [];
var obj;
var previousImgWidth = 0;
var imgSrc;
var passBy = false;
var whichWay;
/*
 * translator is called everytime when it recognizes a voice
 */
translator.voiceToText(function (text) {
    data = text;
    worked = 1;
    document.getElementById("title").innerHTML = data;
    var parts = data.split(" ");
    /*stop functionality*/
    if (document.getElementById(parts[1]) !== null && parts[2] === "stop") {
        stop();

    }
    /*Do you want to pass by the person?*/
    if (passBy === true) {
        if (parts[1] === "yes") {
            speed = setInterval(passingDown, 5);

        }

        passBy = false;
    }
    /*for loop is uses for a long sentense*/
    for (i = 0; i < parts.length - 2; i++) {
        /*creation of the image*/
        if (parts[i] === "new" && parts[i + 1] === "person" && parts[i + 2] !== undefined) {
            if (document.getElementById(parts[i + 2]) !== null) {
                document.getElementById("title").innerHTML = "The Person already Exists!";
            }
            else {
                search(parts[i + 2]);
                searchedName = parts[i + 2];
                document.getElementById("Image").innerHTML = "<input type = 'text' id = 'box1' name = 'first' value = 'copy and paste image url'> <button onclick = 'createImage()'> VoToAn </button>";
                document.getElementById("Image").style.display = "inline";
            }
        }
        /*the boolean is used here to prevent calling one type of shift more than once*/
        var shiftLeft = false;
        var shiftRight = false;
        var shiftTop = false;
        var shiftDown = false;
        var runLeft = false;
        var runRight = false;
        var runUp = false;
        var runDown = false;
        /*stop function can be called through out the sentence*/
        if (document.getElementById(parts[i]) !== null && parts[i + 1] === "stop") {
            stop();

        }
        /*calling of the movement function*/
        if (document.getElementById(parts[i]) !== null && parts[i + 1] === "shift" && parts[i + 2] !== undefined) {

            if (parts[i + 2] === "left") {
                if (!shiftLeft) {
                    move(parts[i], "left");
                }
                shiftLeft = true;
                shiftRight = false;
            }
            if (parts[i + 2] === "right") {
                if (!shiftRight) {
                    move(parts[i], "right");
                }
                shiftRight = true;
                shiftLeft = false;
            }
            if (parts[i + 2] === "up") {
                if (!shiftTop) {
                    move(parts[i], "up");
                }
                shiftTop = true;
                shiftDown = false;
            }
            if (parts[i + 2] === "down") {
                if (!shiftDown) {
                    move(parts[i], "down");
                }
                shiftDown = true;
                shiftTop = false;
            }

        }
        /*calling of run function*/
        if (document.getElementById(parts[i]) !== null && parts[i + 1] === "run" && parts[i + 2] !== undefined) {
            if (parts[i + 2] === "left") {
                if (!runLeft) {
                    run(parts[i], "left");
                }
                runLeft = true;
                runRight = false;
            }
            if (parts[i + 2] === "right") {
                if (!runRight) {
                    run(parts[i], "right");
                }
                runRight = true;
                runLeft = false;
            }
            if (parts[i + 2] === "up") {
                if (!runUp) {
                    run(parts[i], "up");
                }
                runUp = true;
                runDown = false;
            }
            if (parts[i + 2] === "down") {
                if (!runDown) {
                    move(parts[i], "down");
                }
                runDown = true;
                runUp = false;
            }


        }
        /*emotion*/
        if (document.getElementById(parts[i]) !== null && parts[i + 1] === "is" && parts[i + 2] !== undefined) {
            if (parts[i + 2] === "angry" || parts[i + 2] === "tender" || parts[i + 2] === "excited" || parts[i + 2] === "happy" || parts[i + 2] === "sad" || parts[i + 2] === "scared") {
                createEmotion(parts[i], parts[i + 2]);

            }


        }
    }
    ;



});
/*
 * when said yes move the image down
 */
function passingDown() {
    if (setsOfData[obj.name].ypos >= 250) {
        clearInterval(speed);
        passing();
    }
    setsOfData[obj.name].ypos++;
    obj.style.top = setsOfData[obj.name].ypos + 'px';

}
;
/*
 * resest the boundary for the image to move and call moverightPassing or left
 */
function passing() {
    var i, z;
    var restricted = [];
    obj = document.getElementById(searchedName);
    var temp = parseInt(obj.name);
    var difference = [];
    var index = [];
    var smallest;
    var theIndex = 0;
    if (whichWay === "right") {
        /*getting all the boundaries*/
        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp) {
                continue;
            }
            if (setsOfData[i].offset > setsOfData[temp].offset) {
                difference[z] = setsOfData[i].offset - setsOfData[temp].offset;
                index[z] = i;
                z++;
            }
        }
        /*rearranging the boundary so that the closest one is known*/
        for (i = 0; i < difference.length - 1; i++) {
            if (difference[i] < difference[i + 1]) {
                smallest = difference[i];
                var temp = difference[i];
                difference[i] = difference[i + 1];
                difference[i + 1], temp;
                temp = index[i];
                index[i] = index[i + 1];
                index[i + 1] = temp;
                theIndex = i + 1;
            }
            else {
                theIndex = i + 1;
            }
        }
        /*the closest one is skipped so that the image can pass by other image*/
        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp || i === index[theIndex]) {
                continue;
            }
            if (setsOfData[i].offset > setsOfData[temp].offset) {
                restricted[z] = setsOfData[i].offset - setsOfData[i].width;
                z++;

            }
        }
        speed = setInterval(moveRightPassing, 5, restricted, index[theIndex], setsOfData[temp].offset);
    }
    if (whichWay === "left") {
        /*getting all the boundaries*/

        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp) {
                continue;
            }
            if (setsOfData[i].offset < setsOfData[temp].offset) {
                difference[z] = setsOfData[temp].offset - setsOfData[i].offset;
                index[z] = i;
                z++;
            }
        }
        /*rearranging the boundary so that the closest one is known*/

        for (i = 0; i < difference.length - 1; i++) {
            if (difference[i] < difference[i + 1]) {
                smallest = difference[i];
                var temp = difference[i];
                difference[i] = difference[i + 1];
                difference[i + 1], temp;
                temp = index[i];
                index[i] = index[i + 1];
                index[i + 1] = temp;
                theIndex = i + 1;
            }
            else {
                theIndex = i + 1;
            }
        }
        /*the closest one is skipped so that the image can pass by other image*/

        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp || i === index[theIndex]) {
                continue;
            }
            if (setsOfData[i].offset < setsOfData[temp].offset) {
                restricted[z] = setsOfData[i].offset + setsOfData[i].width;
                z++;

            }
        }
        speed = setInterval(moveLeftPassing, 5, restricted, index[theIndex], setsOfData[temp].offset);
    }

}
;
function moveRightPassing(restricted, index, originalPos) {
    setsOfData[obj.name].offset = obj.offsetLeft;
    /*if the restricted one is passed then you need to stop*/
    for (var i = 0; i < restricted.length; i++) {
        if (setsOfData[obj.name].offset === restricted[i]) {
            document.getElementById("title").innerHTML = "You Bumped into a Person. Do you want to pass by the person?";
            whichWay = "right";
            passBy = true;
            clearInterval(speed);
        }
    }
    /*how much the obj must move to completely pass by the one that is next to it*/
    if (setsOfData[obj.name].offset === (originalPos + (2 * setsOfData[index].width))) {
        clearInterval(speed);
        move(searchedName, "up");

    }
    /*movement*/
    setsOfData[obj.name].pos++;
    obj.style.left = setsOfData[obj.name].pos + 'px';//here

}
;
function moveLeftPassing(restricted, index, originalPos) {
    setsOfData[obj.name].offset = obj.offsetLeft;
    /*if the restricted one is passed then you need to stop*/
    for (var i = 0; i < restricted.length; i++) {
        if (setsOfData[obj.name].offset === restricted[i]) {
            document.getElementById("title").innerHTML = "You Bumped into a Person. Do you want to pass by the person?";
            whichWay = "left";
            passBy = true;
            clearInterval(speed);
        }
    }
    /*how much the obj must move to completely pass by the one that is next to it*/
    if (setsOfData[obj.name].offset === (originalPos - (2 * setsOfData[index].width))) {
        clearInterval(speed);
        move(searchedName, "up");

    }
    /*movement*/
    setsOfData[obj.name].pos--;
    obj.style.left = setsOfData[obj.name].pos + 'px';//here

}
;
/*stop the movement by clearing interval*/
function stop() {

    if (speed !== undefined) {

        clearInterval(speed);
    }
}
/* change the image by setting the src attribute to something else*/
function createEmotion(name, feeling) {
    searchedName = name;
    var obj = document.getElementById(name);

    if (feeling === "angry") {
        obj.setAttribute("src", "data:image/jpeg;base64,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");
    }
    if (feeling === "tender") {
        obj.setAttribute("src", "data:image/jpeg;base64,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");
    }
    if (feeling === "excited") {
        obj.setAttribute("src", "data:image/jpeg;base64,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");
    }
    if (feeling === "scared") {
        obj.setAttribute("src", "data:image/jpeg;base64,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");
    }
    if (feeling === "sad") {
        obj.setAttribute("src", "data:image/jpeg;base64,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");
    }
    if (feeling === "happy") {
        obj.setAttribute("src", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxIQEBUQEhIVFRUVEA8QEBUQEBAVEBUQFRUWFhYVExUYHSggGBomGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGi0lHx8tLS0tLS0tLS0tLS0tLS0tLS0tLSstLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0rLf/AABEIAMwAzAMBEQACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABAUBAwYCB//EAD8QAAEDAgMFBAgDBgYDAAAAAAEAAgMEEQUhMQYSQVFxB2GBsRMiMkJykaHRYsHhFDNSc4KSIzRDRIOyU1Si/8QAGgEBAAIDAQAAAAAAAAAAAAAAAAEDAgQFBv/EACsRAAICAQQCAQQCAgMBAAAAAAABAgMRBBIhMQVBURMUImEyUjNCFSNxkf/aAAwDAQACEQMRAD8A+4oAgCAIAgCAIAgCAIAgCAIQEAQkIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIDF1GQYc4DXLqjaXZKTfRDqMXgj9qRv9wuqZ6mqPbLo6ayXSK2ba2Bugc7pZasvJUxNmPjrWaDtnF/4pP/AJ+6p/5en4ZZ/wAVZ8o9M2yhOsbx13fuso+VqfpkPxdq9om0+0lO/wB/d+LJbENdTL2UT0NsfRYw1cb/AGXtPQhbMbYS6ZrSrlHtG66sMDKjICkBAEAQBAEAQBAEAQBAEAQBAYQEesrY4hvPcAFTZdCv+TLK6p2PEUc1XbWkndhZf8TvsuTf5ZR4rOnV4z3YylnqJpvbeeg0XJs1ttnbOhCqqCwka20a1XNy7M92Ojcyj7k2t9Ixd2PZtGHu/hKyVVnqJh9ZfJh1CeLT8lLqs9olXr5ND6QLBprtGasb9msU7m5tcQe4rOF810yfxfaJ9Hj1RDkbPH4tfmujT5SyDw+TWt0NNnPTOjw3aSGXI+o7kdPArsUeQrs7OXdobIdcouQ64ut9NNZRptej0pICAIAgCAIAgCAIAgCAIDyXKG0lljBzuM7StYTHF6zud/VC5mr8jGviPZ0dPoJS/KXRzEgfM7fkdvH6Bedu1M7HmTOxFQrWIom0mHOdo37LGqmyx/iimy+MOy4gwUe8fALpVeMXcmaU9Y30WEVDG3Ro8VvQ0tcPRqyunL2SGtA0CuUYrpGDbZ6us8mJhQ8Psk1yQNdq0FVuqt8NGSnJeyHPhMbtMj3fZatnj4S6LoamaKurwZwzHrDu1XOt8fOv+HJuVatPsp56Puz6ZrS3OLw+zdjYmSsNxqamNj67OXEdF1NL5Kdf4z6Ne/RQt5j2dlh2JRzt3mHhmL5jqF6Gm+FqymcS6iVTxImK8pMoAgCAIAgCAIAgCA8veALk5DMqG8LJKWXg4zHcedKTFEbN0c7iei8/rfINvZA7ek0SrW+ZWU1L3ZriZcpfs3ZWJLgv6HCvef8AL7ro6bQr+UznW6l9RLeNoaLAWC6sUo/x4NFtvs9LPIF03EGbqMgXTIF0yBdMgwmSRdMgi1dG2QZix5jVa92nrtWGuS2FsolBXUBYcxlwPBcS7TyqeH0dKm5P2V0T3wP34zY8RwI71lp9RKt5TL5QhatrO1wTGG1DeTh7Q7+5eq0mqV8f2cHVaWVMv0Wt1tmqEAQBAEAQBAEB5JUN4C5OP2mxgvd6CM5DKQjnyXC8lrv9IM7Wg0qS3zKylp7WyXAk23wbspf/AA6HD6ING8deHcurptKoLMjm33bnhFhddDejWM3UbhgXTcMC6bhgbybhgbybhgbybhgbybhgXTcMC6bhgxdN+Bg8SsDhYrGyMZrkmMnFlBiFFuHuOi4epodUsro6dNu9fsq2ufC8SMNiPqORWVGolXJSibP042RcZndYRiTaiMPGujhxBXrNPqI2wUked1FDrk0yeFslBlAEAQBAEBhAUW0+K+iZuNPrvyHMA8Vz9fqfpQaXZvaHT/Unl9I5Wkg4nUm56leSlLc8s7cnjj0XmG0/vEdFu6OrLzI0L5+kWl10dxqAFSmRgzdRuGBdNwwLpuGBdNwwLpuGBdNwwLpuGBdNwwLpuGBdTuGDF0yTg1zMDgQVXOKnFpmUXh8FBVQWJC4kouEmmdOueVkj4ZWGmmDvdcQH8uq6HjtS6549EampXQ/Z38Moc0OBuCAR0K9XGW5ZR52ScXhmxZEBAEAQBAap5A1pJ0AWEpbY5MordLB89qqg1EzpDpezfhXkdbe7Js9HTWqq1EnU0WgWnCOWkV2S4LpgsLLrxaUcI0Zcs93TcRgXTcRgXU7hgXTcMC6bhgXTcMC6bhgXTcMC6bhgXTcMC6bhgXTcMC6jcTgwXKd3JGCHXx3F/mtLVxz+SNip84KSshuFpJ4eUb8JY4L3ZDELtMLjmz2fhXqfGajdHazleRp2y3o6YLqnMMqQEAQGCgOd2vrdyMMGrzbwGq5vkrlXX/6dDQU755+DnqKKwXkmzsWyyy2o253V2nXOTUtfBNutzca+BdNwwLpuIwLqd2Rgi1eKwRG0k0bDyfI1p+pWai30DfDO143mODgdC0gj5hYt47GD3dY7kMC6bhgXTcMC6nchgiVOLU8R3ZJ42Hk+RgPyJWSjJkEmOVrhvNIcDoWkEfMLF5RJ6uo3DAum4YF03E4PEguCFjPmOCVwyqnYud0zdiyDTTmCdsnC9ndCt/QX/Tt/9MtRX9SrHwfQ4n3AK9guUebksM2KSAgCAw5CGcLtHN6Sp3eDBbxK8x5azdZg9BoIbasmadq4xbMn062KuEa0+TddW7jAXTcQLpkHN7Z7QmkiDI7elkFm8d0Z+tr3LYohu5ZDPmklM6Ul8ji9xzJcb593JbTsS6JUCfgeLS0Egc1xMRI9IxxJG7pcciLpJRmiGsH1ulqWyMbI03a4Ahc+f4vARtusdxOBdNwwcptxtE6maIIT/ivGZ13GcT1W1RBNbmY+z50aIvO88lxOZLiSbrZdiXRlsLbZzG5aGUAuLoXENe1xJ3c/abyWM0prgxawfV4pQ5oc03DgHAjiCFz28PBke7qNwwLpuJF0yMEKYLUl2XxKqvjySLwzag/R2OzlT6SBp5Cx6hez0Vm+lHn9XDZay2W2awQBAa6h1mkrGTwskxWXg+dMfvyOeeLl43WWbrmelhHZUi0hC0yuTJEZVkZYKWj3dZ7jHA3k3DA31G8YPlu0dQZ62Rx0aRG3uDdR87rqQ4iYpZPDALKt5ZbjBqqYwQsoSIkjrOz6rLoHQk/u3nd+E2/VautW15+TCJ1V1pybLMC6ZeUQz5TiUxnqpZDn67mt+FpNl2YrbBIwXZua0KvllhHq4gQs4PBjKJ3WwlYX0gYczG4x/wBPuj5WWpqXtefkxSOiDlrqZOBdNwwC5Q5E4NLlU3ksiQKpuSL2XQfKLPYyf22cjceN16bxNma8HO8lDE8nWLsnLCAICBjT7QvP4HeRVN7xWy2hZsRwmH6LxVrzLJ6SfwW0SrNaRtUGIupyQLpkC+vQ+SLshnyeJrpJ3NaLvdK4ADmXFdmT2wTK1Lad/Q7I0zGD0+895AvZxAB5DdWjPUYlwYfnLlFLtTs5+zsM0JLo/fBzcy/5K2q5SeCVJ9M0dnb/APHmA09Ew/UrLWx/BGSfJ3a5mS1Guc2Y4/hd5LOKzJESPlOFwPmkEcYu9zj4Z5k9y7Nr2rLKt2D6FS7I0rGgS70j7esQ5wAPcAudLU88GOZS6Od2q2f/AGUCWMl0RNs/aabXz7vstim5SZkptcMk9nbvVmHDfB8bBV61dGUTsLrRLAmSQoyDBQESpClFsTOyz7VJHNpXc8O/zaKPJL/rTO6C9GcMIAgK3aD/AC8nwP8AIrX1X+Jl+m/yI4jD9F4mfLZ6O35LWMrHJqs2IYhAEIMhSiGUuCbMtpaiSoLg4uc4wge6HZm+WuZW1bqd0UkUKLb5LolajfOTYSwYexr2uY8Xa5pa4dxUwm4vKMJwz0U2z2zQonyv3w7fO6y18mDMA9+a2b9TvikVwTzyXV1q7sl4tfI6EEHoVKnhmMuim2b2abROkkLg97i4R292Mm/zWzqNVvWEUxi2+S5utXKwbGEujxUQNljdE/2XtLT3X4hZ12OEslc4ZKzZzAf2Jj2l4eXybwI4MGQv3q6+/wCpj9GNaZbLWLjF0JCAwShKI1QU9lkDxs5/mv6Hfkuz4h/9rRX5H/EjvAvTnAMoAgK/HG3geObSPoqr1mtltLxNHCUJyXiLFtk0ekm8xRaxlUFDRsupyYniadsY3nuDRxLiAFMVKXRi2IKhkguxzXD8LgUkpLtEZNyrbQygp3Dgyo3DJmyZGTFkyQZsmSRZMkCyZBhMkmLKdwChtDhGHZZnLqskMkZldE5242Rhd/CHC6s2TxnBKkb7rBPJkYJTIRFqCpLYHrZht6gn8Nl3/EQ/7HI1fIyxWjugvRHEMoAgI9cy7CO5Q1nglPDyfPWt3JHN5FeK1cNtskemralWmWUZWkypm4LExZxHatG4wQ2JDfSkPsdcja66viZJSlkqfLOBpPSxZxPkadfUcT5rrySmuVklxUUXFJtpXQ5GQPHKRlj81rWaGmXox4Lql7S3j97AD/LP3WrLxUf9ZDJbU3aPSu9tkjOoH3WtPxVqf4vIyywh26oHf6tviAH5qqXjr16I3Mlt2poj/uGeJCqeju/qRuNw2ho//Zj/ALwsftL/AOpO48P2lox/uI/BwWS0l39RuI822NC3WceFj+ayWhvf+o3MgT9oVCPZc93Ro+6uj4y59k7isqe0yPSOneeRfYBXx8S/9pDLKar7Q6x+TGxx8si4rZj4ymPeWCnqsVrKi5kmkI4hoLW+S240V1/xiTmOcZPGztO4VsG6XXMw94m443S+S+jLKDikfbnDNeV/ZYujW5ZIyRDqis0W1ostkIc3P5nyXqfEV4rcjm+SnmSh6OvXYOWEAQHl4uEyDgsbi3J7/wAQ+q8z5erbZn5O5oZ7qjZAVwnwbElySmrBlTKDb2kMlC8jWMiTwGq3NBPbdj5KpPBzPZvKx5ljLQXZPFwD6lgLfQr1umUc4Zpaqcn0dpNgFNL7cEbv6Atp1wz0asbJ47IU2wNE/MRlvwOcB5rB0Rz0Zq+RBn7MIT7Er29c/O6remTZYtS12RZOyg+7UX6sbbyR6Z+mZfdEY9ks/CWPxCr+2n8mX3SPB7JKrg+LxT7afyPuonodklRxkj8An20/kfdI3Rdk8nvTtHwtB/JZR08yHqkSY+yto9qocejQPILJ6b9mP3RNi7OKRvtb7j3vdbzWS06+DB6iRNi2So49IGEjiRc/VZqmK9GDukxicEMML3FjA1rDezGqZwgoZIg57uzguz+m9NXNfb1WMfJ0NwB5rzvkZ7amvlnUjLKR9VK88XI1SFZIzRW1r8ldXHJdA6zZin3Ih0v4le10cNlKODq57rWXa2jWCAIDBQHL7XUlxvgaZ/dc3yVH1as/BvaC7bPkpqSS4XkJpnZmiwjVLNdmZYRI10btHscw/wBQspU9rUyqaPjdRDNQVbvRndfG82yycy9xfuXq9NqcxUka069x1mF9o7BYVMLmni+IXb/br9V0YamL7NSVLR12GbVUU9tyoZc+687r/wC0q9WRZXtZ0ULgdCD0IKzyjElsCDJvaEwMm0BMDJ4eEwMo0vCDJGl+XVOAVWI4nBCLyzRs+J7Qocoonazk8U7Q6KPKPfmd+AWZ4OOSqlfFIyVbOH2g2mnrhuFojivfdGruW8VqWancsI2q6cHa9neFehpjM4etKcr6+jyI8gvM+Sv3ywvRswXJ1BXPZsIjyuWcUWRRAZGZJQ3he56BdLx9DstX6J1FirrO/o4t1gHcvYpY4POt5JCkgIAgCAiYjT77CFjKO5YJi8NNHBlhikLDzy6Lx/kKHVN/B6OixWwLGB65rWCtokgKt9lbOY26wT0rBVMHrsFpQNTGLm/ecyt/RX7fwbKlw8HENoWvGi6e/Ba4KRHlwQHgrFqGiqVCPEVHPEbxTSx/y5Xt8iro6plT05Y020OJxezVSO/mue/zKujqyt0fosGdoOLN9+E9YTf/ALKz7xGH0DaO0nFOUJ/4z90+8H0Dy7tGxU6GEf8AET+afeD6BEqNssVlyMzW/wApjmH6FQ9aiVQVlRUV037yqncP4TPJu/K6olqyxUfojx4KSbnM8bm6plqWy2NBLjwoN4BVO5stVSRYYHgZqpxGBZg9aR3JvJVXXqEM+yJ4XR9Q3A0BrRZrWhrQODRkFwpScnliKNbyhaivq5bBXxXKSNiK9llsvQ7x9IRr5L1fjNNshufbOV5C7dLajrrLqHOMqQEAQBAEByu02Ge+3r+i0NdpfrQ4NzSaj6csMpqObgvHTrcW0ztzSksos43KhmtJEhn00I4Ed6wx7KZI4jaXADTuM0QvE43cB7h+y6mn1CsjiXYhPb2VkLgQr5ZNjKZt9CCsdzJPJpQm9kYPJoxyU7yNqMfsQ5Kd42oCjHJRvG1HoUg5JuJwj0IAoySHMAUptjg1Q0z55BFELuOvcOZUuagssrnYkd/hOGMpYhE3M6vdxcVybrPqyyULl5JDysEXIiTyWVkUXRiQIIjNIGjQHP7LreN0krJZZGovVUMHd4dSiNgHcLr1kY7Vg8+228slrIgIAgCAIAgNU8Ie0tPFAcRjGHmF5cBlx+64Pk9BuW+B19Hql/CR5pai683OPpm7OGOUWMUipaNeUTe11xYi4OoOix5TyimUTl8Z2VsTLS6amM8+O6t+nVp8TEZuPZzwkLTuvBa4ahwsVt98ovUkyQ1wKweTPJsssQLIBZAeSpRJolmAWaT+DFyJOGYNNVHIbkfF7gRcfhWM7oVrkplb6Ozw3DoqZm5GMz7Tj7Tj1XMsulY/0VpN8s3PcsEi1IizzLNLJdGOCslkLzutW9ptNK6aS6LJzVcN0uzqtnsLEbd4jPh917DT0KqCSOFfc7JF6tgoCAIAgCAIAgMKMAjV1GJW2Iz4FGsrDEeHk4nEMPdC82HhwXB8h47/AHgdjSaxNbJmaaquvOzg12bsofBOimVLRRKJIjktosOiqUc9nisooZxaWNrjwdYb/wA9VnC6yPTKnFrooqrY/jDNb8MlyFtQ1f8AZZG9rsr5dnqxnuB3e148ir1qKmWK1Ec4fVD/AEXfRT9Wv5J+sjYzB6t2kJ/qc0I7q17IdqJkOyU7v3kjWDiBm77KuWqrXCRg7X6Lii2dpocy30rucmYv00+i1p6qx/xeCMyZZOk4DIcAMgqOX3yZxh8mp8inBaokSadZpFsYFdJKXndb4re02kndLGODOc4VLdLs6LAcGt67h395XrdNpY0xwjh6jUSullnSgWW0a6RlAEAQBAEAQBAEAQEespWyNsR0KjCfDJXycjiuCujO8PAj81zNX42Fq3R7N7T62UPxl0V0dQW5O/Rebv0llX8kdVSrtWYMnQ1IWnKODGVZKZKsMFLi0bRIowYuJsbL3rHaYuB79MeacmOw8mY802tkqB4dIpUcGSia3SrLBkokeScKdpaqyHPVgK2NTb4RYofJqip3ym1jb6rsaTxc5vM+jXv1kK1iHZ1GEYIGAOcOg+69HTRCpYijkWWyseWXgCuKjKAIAgCAIAgCAIAgCAIDy9gIsUBT4hgTX5ty7joq7Ko2LEkZwslDo5urwmSM8R5LkajxMX/A6NPkP7kYSvbqPkuRb462Po3oXV2dGxlctKVE49oz2p9G9taFU4GLpZ7FYE2GP0n8GDWBNpKqfwan1wUqGTL6eOzQ6sJ0BWxDSzl6J/BdsNhkf+mZXSo8TObyzXnra4rgt8O2ecczl11Xbo0FVXrk5tusnZwdJR0DIxkM+fFb3rBqfslIDKAIAgCAIAgCAIAgCAIAgCAIDw5gIsRdMtEcMg1GDxv4W6KGTl+isn2bvofmAqZ6aqX8kXR1FkemQJtm3j9FrT8bRLpGxHXWrs0HZ+TkfmFQ/D1ssXkpmWbPSHgfms4+Kqj2YvyFj6JsGzJ42WxDQ0R9FM9Zc/ZY0+z7Bqb+C2owjHpGvKcpdssoKNjNGhZZfoxfJICAygCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAIAgCAID//Z");
    }
    obj.addEventListener('click', function () {
        originalImg(obj);
    });

}
;
/*sets the image back to its original src*/
function originalImg(obj) {
    var temp = parseInt(obj.name);
    obj.removeEventListener('click', function () {
        originalImg(obj);
    });
    obj.setAttribute("src", setsOfData[temp].imgSrc);

}
/* creation of image after calling new person*/
function createImage() {
    document.getElementById("Image").style.display = "none";
    var para = document.createElement("img");
    para.id = searchedName;
    para.name = z;
    if (previousImgWidth !== 0) {
        para.setAttribute("style", "position:absolute; margin-left:" + previousImgWidth);
    }
    else {
        para.setAttribute("style", "position:absolute;");

    }
    para.setAttribute("src", document.getElementById('box1').value);
    var element = document.getElementById("content");
    /*put the para inside the element so that it is part of HTML*/
    element.appendChild(para);
    var img = document.getElementById(searchedName);
    setsOfData[z] = new dataSet(0, 0, img.clientWidth, previousImgWidth, img.offsetLeft, img.getAttribute('src'));
    previousImgWidth += img.clientWidth;
    z++;
}
;
/*
 * move the object until it hits another obj or the boundary
 */
function move(name, direction) {
    var i, z;
    /*contains the boundaries*/
    var restricted = [];
    obj = document.getElementById(name);
    /*obj.name apparently is string so make it number*/
    var temp = parseInt(obj.name);
    searchedName = name;


    if (direction === "left") {
        clearInterval(speed);
        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp) {
                continue;
            }
            /*setting the restricted*/
            if (setsOfData[i].offset < setsOfData[temp].offset) {
                restricted[z] = setsOfData[i].offset + setsOfData[i].width;
                z++;
            }
        }

        speed = setInterval(moveLeft, 5, restricted);


    }
    if (direction === "right") {
        clearInterval(speed);
        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp) {
                continue;
            }
            /*setting the restricted*/
            if (setsOfData[i].offset > setsOfData[temp].offset) {
                restricted[z] = setsOfData[i].offset - setsOfData[i].width;
                z++;
            }
        }

        speed = setInterval(moveRight, 5, restricted);



    }
    if (direction === "up") {
        clearInterval(speed);
        speed = setInterval(moveUp, 5);


    }
    if (direction === "down") {
        clearInterval(speed);

        speed = setInterval(moveDown, 5);


    }




}
;
/*same as move but different speed*/
function run(name, direction) {
    var i, z;
    /*setting the boundaries*/
    var restricted = [];
    obj = document.getElementById(name);
    /*obj.name apparently is string so make it number*/
    var temp = parseInt(obj.name);


    if (direction === "left") {
        clearInterval(speed);
        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp) {
                continue;
            }
            /*setting the restricted*/
            if (setsOfData[i].offset < setsOfData[temp].offset) {
                restricted[z] = setsOfData[i].offset + setsOfData[i].width;
                z++;
            }
        }

        speed = setInterval(moveLeft, 1, restricted);


    }
    if (direction === "right") {
        clearInterval(speed);
        for (i = 0, z = 0; i < setsOfData.length; i++) {

            if (i === temp) {
                continue;
            }
            /*setting the restricted*/
            if (setsOfData[i].offset > setsOfData[temp].offset) {
                restricted[z] = setsOfData[i].offset - setsOfData[i].width;
                z++;
            }
        }

        speed = setInterval(moveRight, 1, restricted);



    }
    if (direction === "up") {
        clearInterval(speed);

        speed = setInterval(moveUp, 1);


    }
    if (direction === "down") {
        clearInterval(speed);

        speed = setInterval(moveDown, 1);


    }




}
;
function moveLeft(restricted) {
    setsOfData[obj.name].offset = obj.offsetLeft;
    /*if the restricted is found than you need to make sure that the user wants to go left*/
    for (var i = 0; i < restricted.length; i++) {
        if (setsOfData[obj.name].offset <= 0 || setsOfData[obj.name].offset <= restricted[i]) {
            document.getElementById("title").innerHTML = "You Bumped into a Person. Do you want to pass by the person?";
            whichWay = "left";
            passBy = true;
            clearInterval(speed);
        }
    }
    /*reached the boundary*/
    if (setsOfData[obj.name].offset <= 0) {
        clearInterval(speed);
    }
    setsOfData[obj.name].pos--;
    obj.style.left = setsOfData[obj.name].pos + 'px';


}
;
function moveRight(restricted) {
    setsOfData[obj.name].offset = obj.offsetLeft;
    /*if the restricted is found than you need to make sure that the user wants to go right*/
    for (var i = 0; i < restricted.length; i++) {
        if (setsOfData[obj.name].offset >= restricted[i]) {
            document.getElementById("title").innerHTML = "You Bumped into a Person. Do you want to pass by the person?";
            whichWay = "right";
            passBy = true;
            clearInterval(speed);
        }
    }
    /*reached the boundary*/
    if (setsOfData[obj.name].offset >= 1400) {
        clearInterval(speed);
    }
    setsOfData[obj.name].pos++;
    obj.style.left = setsOfData[obj.name].pos + 'px';//here


}
;
function moveUp() {
    /*reached the boundary*/
    if (setsOfData[obj.name].ypos <= 0) {
        clearInterval(speed);
    }
    setsOfData[obj.name].ypos--;
    obj.style.top = setsOfData[obj.name].ypos + 'px';


}
;
function moveDown() {
    /*reached the boundary*/
    if (setsOfData[obj.name].ypos === 250) {
        clearInterval(speed);
    }
    setsOfData[obj.name].ypos++;
    obj.style.top = setsOfData[obj.name].ypos + 'px';


}
;
/*creation of dataSet obj that contains info of new image crom createImg method*/
function dataSet(pos, ypos, width, previousImg, offset, imgSrc) {
    this.pos = pos;
    this.ypos = ypos;
    this.width = width;
    this.previousImg = previousImg;
    this.offset = offset;
    this.imgSrc = imgSrc;
}
;
function search(hello) {
    window.open("https://www.google.com/search?q=cartoon&biw=1655&bih=906&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiVmrrensXOAhWBcCYKHX1SBvIQ_AUICSgE#tbm=isch&q=cartoon+" + hello);

}
;

