(() => {
  let $idCounter = 0;

  document.querySelectorAll("[data-carousel]").forEach(($carousel) => {
    const $id = findId($carousel);
    const $prev = $carousel.querySelector("[data-carousel-arrow-prev]");
    const $next = $carousel.querySelector("[data-carousel-arrow-next]");
    const $items = $carousel.querySelectorAll("[data-carousel-item]");
    const $itemAmount = $items.length;
    let $index =
      $carousel.dataset.carouselStart !== undefined
        ? 0
        : parseInt($carousel.getAttribute("data-carousel-start"));

    $prev.addEventListener("click", () => {
      updateCarousel(false);
    });

    $next.addEventListener("click", () => {
      updateCarousel(true);
    });

    function updateCarousel($incOrDec) {
      let $tmpIndex = $incOrDec ? $index + 1 : $index - 1;

      if (
        !($index <= 0 && !$incOrDec) &&
        !($index >= $itemAmount - 1 && $incOrDec)
      ) {
        toggleElementClass($items[$index], "active", false);
        toggleElementClass($items[$tmpIndex], "active", true);
        $incOrDec ? $index++ : $index--;

        updateCarouselArrows();
        window.dispatchEvent(
          new CustomEvent("carouselValueChanged", {
            detail: { id: $id },
          }),
        );
      }
    }

    function updateCarouselArrows() {
      if ($index >= $itemAmount - 1) {
        toggleArrowEnabled($next, false);
      } else {
        toggleArrowEnabled($next, true);
      }

      if ($index <= 0) {
        toggleArrowEnabled($prev, false);
      } else {
        toggleArrowEnabled($prev, true);
      }
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
    if ($carouselParent.dataset.dataCarouselId !== undefined) {
      return $carouselParent.dataset.dataCarouselId;
    } else {
      $idCounter++;
      return $idCounter;
    }
  }
})();
