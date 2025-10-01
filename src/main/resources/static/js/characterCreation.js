(() => {
  //Initialisation with Dto Values
  const $attrs = $INIT.attrs;
  const $carouselArrowLeftPrefix = $INIT.attributeArrowLeftPrefix;
  const $carouselArrowRightPrefix = $INIT.attributeArrowRightPrefix;
  const $carouselInputId = $INIT.attributeInputPrefix;
  const $attributePrefix = $INIT.attributePrefix;
  const $abilityTreeItemPrefix = $INIT.abilityTreeItemPrefix;
  const $attributeMin = $INIT.attributeMin;
  const $pointsDisplayMessageWPL = $INIT.pointsDisplayMessageWPL;
  const $attributePoints = $INIT.attributePoints;
  const $abilityPoints = $INIT.abilityPoints;
  const $abilitySets = $INIT.abilitySets;
  const $pointsDisplay = document.getElementById("attributePointDisplay");
  const $activeAbilityTreeId = document.getElementById($carouselInputId);
  const $prev = document.getElementById("arrow-left-abilityTree");
  const $next = document.getElementById("arrow-right-abilityTree");
  let $carousel = document.getElementById("abilityTreeCarousel");

  const $state = {
    attrs: [],
    meta: {},
    pool: $attributePoints,
    abilityIndex: getActiveAbilityTreeId(),
    abilitySetsAmount: $abilitySets.length,
  };

  let $abilityTreeIndexState = checkAbilityTreeIndexState();

  //Initialization of Gui and Attributes
  refreshPointsDisplay();
  updateCarouselArrows();

  Object.entries($attrs).forEach(([$key, $attribute]) => {
    $state.attrs[$key] = {
      value: $attribute.value,
      min: $attribute.value,
    };
    document
      .getElementById($carouselArrowLeftPrefix + $key)
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
      .getElementById($carouselArrowRightPrefix + $key)
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
    if ($abilityTreeIndexState == 1 || $abilityTreeIndexState == 3) return;
    $state.abilityIndex--;
    updateCarousel();
  });
  $next?.addEventListener("click", () => {
    if ($abilityTreeIndexState == 2 || $abilityTreeIndexState == 3) return;
    $state.abilityIndex++;
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
      $carouselArrowLeftPrefix + $key
    );
    toggleElementDisabledClass($attributeArrowLeft, $disableLeftArrow);
    toggleElementClass(
      $attributeArrowLeft,
      "leg-transition",
      !$disableLeftArrow
    );
  }

  //When the pool of Attribute Points is exhausted disable all RightArrows
  function refresthAttributeArrowsRight() {
    let $attributePoolExhausted = $state.pool <= 0;
    Object.entries($state.attrs).forEach(([$key, $attr]) => {
      $attributeArrow = document.getElementById(
        $carouselArrowRightPrefix + $key
      );
      toggleElementDisabledClass($attributeArrow, $attributePoolExhausted);
      toggleElementClass(
        $attributeArrow,
        "leg-transition",
        !$attributePoolExhausted
      );
    });
  }

  // ---------- Carousel ----------
  function updateCarouselArrows() {
    let $disablePrev =
      $abilityTreeIndexState == 1 || $abilityTreeIndexState == 3;
    let $disableNext =
      $abilityTreeIndexState == 2 || $abilityTreeIndexState == 3;
    toggleElementDisabledClass($prev, $disablePrev);
    toggleElementClass($prev, "leg-transition", !$disablePrev);
    toggleElementDisabledClass($next, $disableNext);
    toggleElementClass($next, "leg-transition", !$disableNext);
  }

  function updateCarousel() {
    $carousel.getElementsByClassName("active")[0].classList.toggle("active");
    const $activeAbilityTree = document.getElementById(
      $abilityTreeItemPrefix + $state.abilityIndex
    );
    $activeAbilityTree.classList.toggle("active");
    $activeAbilityTreeId = $activeAbilityTree.getAttribute("value");
    $abilityTreeIndexState = checkAbilityTreeIndexState();
    updateCarouselArrows();
    refreshActiveAbilityTreeId();
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

  function refreshActiveAbilityTreeId() {
    document.getElementById("selectedAbilityTreeId").value =
      getActiveAbilityTreeId();
  }

  function getActiveAbilityTreeId() {
    return document
      .getElementById("abilityTreeCarousel")
      .getElementsByClassName("active")[0]
      .id.replace($abilityTreeItemPrefix, "");
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

  /**Checks the ability Index for its state
   * If it is at first and last Index it returns 3
   * If the AbilityTree is at its last Index returns 2
   * If the state is on its first index return 1
   * If its neither first nor last returns 0
   * */
  function checkAbilityTreeIndexState() {
    console.log(
      "AbiltyIndex is at : " +
        $state.abilityIndex +
        ". Amount of ability sets is : " +
        $state.abilitySetsAmount +
        "."
    );
    if (
      $state.abilityIndex <= 0 &&
      $state.abilityIndex >= $state.abilitySetsAmount - 1
    )
      return 3;
    if ($state.abilityIndex >= $state.abilitySetsAmount - 1) return 2;
    if ($state.abilityIndex <= 0) return 1;
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
