(() => {
  const $genderSelectionContainer = document.getElementById("genderSelection");
  const $selectedGenderValue = document.getElementById("selectedGender");
  console.log("piep");
  Object.entries($genderSelectionContainer.children).forEach(
    ([$key, $value]) => {
      $value.addEventListener("click", (e) => {
        console.log("onClick");
        Object.entries($genderSelectionContainer.children).forEach(
          ([$innerKey, $innerValue]) => {
            if ($innerValue.classList.contains("selected"))
              $innerValue.classList.remove("selected");

            if (!$innerValue.classList.contains("leg-transition"))
              $innerValue.classList.add("leg-transition");
          }
        );
        if (!$value.classList.contains("selected"))
          $value.classList.add("selected");
        if ($value.classList.contains("leg-transition"))
          $value.classList.remove("leg-transition");
        $selectedGenderValue.value = $value.getAttribute("value");
      });
    }
  );
})();
