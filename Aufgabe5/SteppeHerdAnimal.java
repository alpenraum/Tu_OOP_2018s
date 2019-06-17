public interface SteppeHerdAnimal extends FitAnimal<SteppeHerdAnimal> {

    /**
     * Compares SteppeHerdAnimal @param this and FitAnimal @param that according to their velocity. Higher velocity means fitter.
     *
     * @param that FitAnimal, which @param this is compared to
     * @return :
     * 1, if SteppeHeardAnimal @param this is much faster than FitAnimal @param that
     * 0, if both FitAnimals are almost equally fast
     * -1, if SteppeHeardAnimal @param that is much faster than FitAnimal @param this
     */
    @Override
    int fitter(FitAnimal that);
}
