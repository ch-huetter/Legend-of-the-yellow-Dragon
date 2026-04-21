(() => {
  //Initialization with Dto Values
  const $attrs = $INIT.attrs;
  const $attributeArrowLeftPrefix = $INIT.attributeArrowLeftPrefix;
  const $attributeArrowRightPrefix = $INIT.attributeArrowRightPrefix;
  const $attributeInputId = $INIT.attributeInputPrefix;
  const $attributePrefix = $INIT.attributePrefix;
  const $attributeMin = $INIT.attributeMin;
  const $pointsDisplayMessageWPL = $INIT.pointsDisplayMessageWPL;
  const $attributePoints = $INIT.attributePoints;
  const $abilityPoints = $INIT.abilityPoints;
  const $playerClasses = $INIT.playerClasses;
  const $pointsDisplay = document.getElementById("attributePointDisplay");
  const $activePlayerClassId = document.getElementById(
    "selectedActivePlayerClassId",
  );

  const $state = {
    attrs: [],
    meta: {},
    pool: $attributePoints,
    playerClassIndex: getActivePlayerClassId(),
    playerClassesAmount: $playerClasses.length,
  };

  let $playerClassesIndexState = checkPlayerClassIndexState();

  //Initialization of Gui and Attributes
  refreshPointsDisplay();
  updateCarouselArrows();

  Object.entries($attrs).forEach(([$index, $attribute]) => {
    console.log($index);
    console.log($attribute);
    let $key = $attribute.attributeKey.key;
    $state.attrs[$key] = {
      value: $attribute.value,
      min: $attribute.value,
    };
    document
      .getElementById($attributeArrowLeftPrefix + $key)
      .addEventListener("click", (e) => {
        let $clickedAttr = $state.attrs[$key];
        if (
          $clickedAttr.value - 1 >= $clickedAttr.min &&
          $clickedAttr.value - 1 >= $attributeMin
        ) {
          $clickedAttr.value -= 1;
          $state.pool += 1;
          refreshPointsDisplay();
          refreshAttributeDisplay($key);
          refresthAttributeArrows($key);
        }
      });
    document
      .getElementById($attributeArrowRightPrefix + $key)
      .addEventListener("click", (e) => {
        let $clickedAttr = $state.attrs[$key];
        if ($state.pool > 0) {
          $clickedAttr.value += 1;
          $state.pool -= 1;
          refreshPointsDisplay();
          refreshAttributeDisplay($key);
          refresthAttributeArrows($key);
        }
      });
  });

  $prev?.addEventListener("click", () => {
    if ($playerClassesIndexState == 1 || $playerClassesIndexState == 3) return;
    $state.playerClassIndex--;
    updateCarousel();
  });
  $next?.addEventListener("click", () => {
    if ($playerClassesIndexState == 2 || $playerClassesIndexState == 3) return;
    $state.playerClassIndex++;
    updateCarousel();
  });

  //Starts the refresth of all Attribute Keys
  function refresthAttributeArrows($key) {
    refreshAttributeArrow($key);
    refresthAttributeArrowsRight();
  }
  function refreshAttributeArrow($key) {
    let $attr = $state.attrs[$key];
    let $disableLeftArrow = $attr.value <= $attr.min;
    let $attributeArrowLeft = document.getElementById(
      $attributeArrowLeftPrefix + $key,
    );
    toggleElementDisabledClass($attributeArrowLeft, $disableLeftArrow);
    toggleElementClass(
      $attributeArrowLeft,
      "leg-transition",
      !$disableLeftArrow,
    );
  }

  //When the pool of Attribute Points is exhausted disable all RightArrows
  function refresthAttributeArrowsRight() {
    let $attributePoolExhausted = $state.pool <= 0;
    Object.entries($state.attrs).forEach(([$key, $attr]) => {
      $attributeArrow = document.getElementById(
        $attributeArrowRightPrefix + $key,
      );
      toggleElementDisabledClass($attributeArrow, $attributePoolExhausted);
      toggleElementClass(
        $attributeArrow,
        "leg-transition",
        !$attributePoolExhausted,
      );
    });
  }

  // ---------- Carousel ----------
  function updateCarouselArrows() {
    let $disablePrev =
      $playerClassesIndexState == 1 || $playerClassesIndexState == 3;
    let $disableNext =
      $playerClassesIndexState == 2 || $playerClassesIndexState == 3;
    toggleElementDisabledClass($prev, $disablePrev);
    toggleElementClass($prev, "leg-transition", !$disablePrev);
    toggleElementDisabledClass($next, $disableNext);
    toggleElementClass($next, "leg-transition", !$disableNext);
  }

  function updateCarousel() {
    $carousel.getElementsByClassName("active")[0].classList.toggle("active");
    console.log(
      "playerClassItemPrefix is " +
        $playerClassItemPrefix +
        " and state is " +
        $state.playerClassIndex,
    );
    const $activePlayerClass = document.getElementById(
      $playerClassItemPrefix + $state.playerClassIndex,
    );
    $activePlayerClass.classList.toggle("active");
    $activePlayerClassId.value = $activePlayerClass.getAttribute("value");
    $playerClassesIndexState = checkPlayerClassIndexState();
    updateCarouselArrows();
    refreshActivePlayerClassId();
  }

  // ---------- Submit: State zurück in die Gui schreiben vor Commit ----------
  /*
  document.getElementById("charForm").addEventListener("submit", (e) => {
    Object.entries($state.attrs).forEach(([$key, $attr]) => {
      document.getElementById($abilityTreeItemPrefix + $key).value =
        $attr.value;
    });
    refreshActiveAbilityTreeId();
  });
  */
  //_____________________Helper________________

  function refreshActivePlayerClassId() {
    document.getElementById("selectedActivePlayerClassId").value =
      getActivePlayerClassId();
  }

  function getActivePlayerClassId() {
    return document
      .getElementById("playerClassCarousel")
      .getElementsByClassName("active")[0]
      .id.replace($playerClassItemPrefix, "");
  }

  function toggleElementDisabledClass($element, $addOrRemove) {
    if ($addOrRemove) {
      if (!$element.classList.contains("disabled"))
        $element.classList.add("disabled");
    } else {
      if ($element.classList.contains("disabled"))
        $element.classList.remove("disabled");
    }
  }

  function toggleElementClass($element, $class, $addOrRemove) {
    if ($addOrRemove) {
      if (!$element.classList.contains($class)) $element.classList.add($class);
    } else {
      if ($element.classList.contains($class))
        $element.classList.remove($class);
    }
  }

  /**Checks the Carousel Index for its state
   * If it is at first and last Index it returns 3
   * If it is at its last Index returns 2
   * If the state is on its first index return 1
   * If its neither first nor last returns 0
   * */
  function checkPlayerClassIndexState() {
    if (
      $state.playerClassIndex <= 0 &&
      $state.playerClassIndex >= $state.playerClassesAmount - 1
    )
      return 3;
    if ($state.playerClassIndex >= $state.playerClassesAmount - 1) return 2;
    if ($state.playerClassIndex <= 0) return 1;
    return 0;
  }

  function refreshPointsDisplay() {
    let $pointsDisplayText = $pointsDisplayMessageWPL.replace("#", $state.pool);
    $pointsDisplay.textContent = $pointsDisplayText;
  }

  function refreshAttributeDisplay($key) {
    $display = document.getElementById($attributePrefix + $key);
    $display.value = $state.attrs[$key].value;
  }
})();
