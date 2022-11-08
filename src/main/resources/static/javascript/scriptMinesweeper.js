import {
    TILE_STATUSES,
    createBoard,
    markTile,
    revealTile,
    checkWin,
    checkLose,
  } from "./minesweeper.js"

  var BOARD_SIZE = 1;
  var NUMBER_OF_MINES = 1;

  if(diff === "1") {
    BOARD_SIZE = 10
    NUMBER_OF_MINES = 5
  } else if(diff === "2") {
    BOARD_SIZE = 10
    NUMBER_OF_MINES = 8
  } else if(diff === "3") {
    BOARD_SIZE = 15
    NUMBER_OF_MINES = 13
  } else if(diff === "4") {
    BOARD_SIZE = 15
    NUMBER_OF_MINES = 30
  } else if(diff === "5") {
    BOARD_SIZE = 20
    NUMBER_OF_MINES = 50
  }
  
  const board = createBoard(BOARD_SIZE, NUMBER_OF_MINES)
  const boardElement = document.querySelector(".board")
  const minesLeftText = document.querySelector("[data-mine-count]")
  const messageText = document.querySelector(".subtext")
  var hours = 0, minutes = 0, seconds = 0, time = "", total = 0;
  setInterval(function () {
    seconds += 1;
    if(seconds === 60) {
      seconds = 0;
      minutes += 1;
      if(minutes === 60) {
        minutes = 0;
        hours += 1;
      }
    }
    total += 1;
    time = "";
    if(hours < 10) {
      time += "0" + hours + ":";
    }
    else {
      time += hours + ":";
    }
    if(minutes < 10) {
      time += "0" + minutes + ":";
    }
    else {
      time += minutes + ":";
    }
    if(seconds < 10) {
      time += "0" + seconds;
    }
    else {
      time += seconds;
    }
    document.getElementById("timer").innerHTML = time;
  }, 1000);

  board.forEach(row => {
    row.forEach(tile => {
      boardElement.append(tile.element)
      tile.element.addEventListener("click", () => {
        revealTile(board, tile)
        checkGameEnd()
      })
      tile.element.addEventListener("contextmenu", e => {
        e.preventDefault()
        markTile(tile)
        listMinesLeft()
      })
    })
  })
  boardElement.style.setProperty("--size", BOARD_SIZE)
  minesLeftText.textContent = NUMBER_OF_MINES
  
  function listMinesLeft() {
    const markedTilesCount = board.reduce((count, row) => {
      return (
        count + row.filter(tile => tile.status === TILE_STATUSES.MARKED).length
      )
    }, 0)
  
    minesLeftText.textContent = NUMBER_OF_MINES - markedTilesCount
  }
  
  function checkGameEnd() {
    const win = checkWin(board)
    const lose = checkLose(board)
  
    if (win || lose) {
      boardElement.addEventListener("click", stopProp, { capture: true })
      boardElement.addEventListener("contextmenu", stopProp, { capture: true })
    }
  
    if (win) {
      //messageText.textContent = "You Win! Go back to the levels screen?"
      var r=confirm("You Win! Go back to the levels screen?");
      if (r) {
        location = "/levels/finish/" + diff + "/" + total;
      }
    }
    if (lose) {
      messageText.textContent = "You Lose"
      board.forEach(row => {
        row.forEach(tile => {
          if (tile.status === TILE_STATUSES.MARKED) markTile(tile)
          if (tile.mine) revealTile(board, tile)
        })
      })
    }
  }
  
  function stopProp(e) {
    e.stopImmediatePropagation()
  }