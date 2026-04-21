/**
 * Mulit Arrow Selection Script. Initialialises automatically.
 *
 * Needs data-mulit-value-arrow-control attribute on root element.
 * With data-multi-value-arrow-control-pool you need to set the start Pool Size
 *
 * Every Row needs the data-mulit-value-arrow-control-row.
 * Arrows need the data-mulit-value-arrow-control-dec/inc attribute.
 * The Input holding the Value that should be changed needs the data-multi-value-arrow-control-value attribute
 *
 * After every Value Change a multiValueArrowControlRowValueChanged Event will be emitted with the Id of the mutliValueArrowControl and its current Pool Value
 *
 */

(() => {
  let $idCounter;
  console.log("Hello World");
  document
    .querySelectorAll("[data-multi-value-arrow-control]")
    .forEach(($control) => {
      console.log("I bims einen schleifen");
      const $id = findId($control);
      let $pool = parseInt($control.dataset.dataMultiValueArrowControlPool);
      const $min = parseInt($valueHolder.value);
      const $increaseArrowList = document.querySelectorAll(
        "[data-multi-value-arrow-control-inc]",
      );

      let $increaseArrowStatus =
        !$increaseArrow[0].classList.contains("disabled");

      $control
        .querySelectorAll("[data-mulit-value-arrow-control-row]")
        .forEach(($row) => {
          const $increaseArrow = $row.querySelector(
            "[data-multi-value-arrow-control-inc]",
          );

          const $decreaseArrow = $row.querySelector(
            "[data-multi-value-arrow-control-dec]",
          );
          const $valueHolder = $row.querySelector(
            "[data-multi-value-arrow-control-value]",
          );

          $increaseArrow.addEventListener("click", () => {
            updateValueControlRow(true);
          });

          $decreaseArrow.addEventListener("click", () => {
            updateValueControlRow(false);
          });

          function updateValueControlRow($incOrDec) {
            console.log("updateRow Called " + $incOrDec);
            if (
              ($pool > 0 && $incOrDec) ||
              ($valueHolder.value > $min && !$incOrDec)
            ) {
              updateValueControlValue(
                $incOrDec ? $valueHolder.value++ : $valueHolder.value--,
              );
              $pool = $incOrDec ? $pool++ : $pool--;
              updateControlArrows();
              window.dispatchEvent(
                new CustomEvent("multiValueArrowControlRowValueChanged", {
                  detail: { id: $id, $pool },
                }),
              );
            }
          }

          function updateControlArrows() {
            updateDecreaseArrow();
            updateIncreaseArrows();
          }

          function updateDecreaseArrow() {
            if ($valueHolder.value > min) {
              toggleArrowEnabled($decreaseArrow, true);
            } else {
              toggleArrowEnabled($decreaseArrow, false);
            }
          }
        });

      function updateIncreaseArrows() {
        if ($pool > 0 && !$increaseArrowStatus) {
          $increaseArrowList.forEach(($arrow) => {
            toggleArrowEnabled($arrow, true);
          });
        } else if ($pool <= 0 && $increaseArrowStatus) {
          $increaseArrowList.forEach(($arrow) => {
            toggleArrowEnabled($arrow, false);
          });
        }
      }

      function updateValueControlValue($newValue) {
        $valueHolder.value = $newValue;
      }
    });

  function toggleArrowEnabled($arrow, $addOrRemove) {
    toggleElementClass($arrow, "disabled", !$addOrRemove);
    toggleElementClass($arrow, "leg-transition", $addOrRemove);
  }

  function toggleElementClass($element, $class, $addOrRemove) {
    if ($addOrRemove) {
      if (!$element.classList.contains($class)) {
        $element.classList.add($class);
      }
    } else {
      if ($element.classList.contains($class)) {
        $element.classList.remove($class);
      }
    }
  }

  function findId($carouselParent) {
    if ($carouselParent.dataset.dataMultiValueArrowControlId !== undefined) {
      return $carouselParent.dataset.dataMultiValueArrowControlId;
    } else {
      $idCounter++;
      return $idCounter;
    }
  }
})();
