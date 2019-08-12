package vn.topica.itlab4.exercises.hieu_trainer.filter_pattern;

import java.util.List;

public class AndFilter implements Filter{
    private Filter filter;
    private Filter otherFilter;

    public AndFilter(Filter filter, Filter otherFilter) {
        this.filter = filter;
        this.otherFilter = otherFilter;
    }

    public List<Drink> meetFilter(List<Drink> drinks) {
        List<Drink> firstFilter = filter.meetFilter(drinks);
        firstFilter.addAll(otherFilter.meetFilter(drinks));
        return firstFilter;
    }
}
