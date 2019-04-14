package br.com.dafle.alodjinha.matcher

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)

fun hasItemCount(itemCount: Int): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("has $itemCount items")
        }

        override fun matchesSafely(view: RecyclerView) = view.adapter?.itemCount == itemCount
    }
}

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int) = atPositionOnView(position, -1)

    fun atPositionOnView(position: Int, targetViewId: Int) = object : TypeSafeMatcher<View>() {
        var resources: Resources? = null
        var childView: View? = null

        override fun describeTo(description: Description) {
            var idDescription = Integer.toString(recyclerViewId)
            resources?.let {
                idDescription = try {
                    it.getResourceName(recyclerViewId)
                } catch (ex: Resources.NotFoundException) {
                    String.format("%s (resource name not found)",
                            arrayOf<Any>(Integer.valueOf(recyclerViewId)))
                }
            }

            description.appendText("with id: $idDescription")
        }

        override fun matchesSafely(view: View): Boolean {

            resources = view.resources
            if (childView == null) {
                val recyclerView =
                        view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                if (recyclerView.id == recyclerViewId) {
                    childView =
                            recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                } else {
                    return false
                }
            }

            return if (targetViewId == -1) {
                view === childView
            } else {
                val targetView = childView!!.findViewById<View>(targetViewId)
                view === targetView
            }
        }
    }

    fun atPositionOnNestedScroll(position: Int, nestedScrollViewId: Int,
                                 nestedScrollViewPosition: Int) =
            atPositionOnNestedScrollOnView(position,
                    nestedScrollViewId, nestedScrollViewPosition, -1)

    fun atPositionOnNestedScrollOnView(position: Int, nestedScrollViewId: Int,
                                       nestedScrollViewPosition: Int, targetViewId: Int) =
            object : TypeSafeMatcher<View>() {
                var resources: Resources? = null
                var childView: View? = null

                override fun describeTo(description: Description) {
                    var outerIdDescription = recyclerViewId.toString()
                    var nestedIdDescription = nestedScrollViewId.toString()
                    resources?.let {
                        outerIdDescription = try {
                            it.getResourceName(recyclerViewId)
                        } catch (ex: Resources.NotFoundException) {
                            String.format("%s (resource name not found)",
                                    arrayOf<Any>(Integer.valueOf(recyclerViewId)))
                        }
                        nestedIdDescription = try {
                            it.getResourceName(nestedScrollViewId)
                        } catch (ex: Resources.NotFoundException) {
                            String.format("%s (resource name not found)",
                                    arrayOf<Any>(Integer.valueOf(nestedScrollViewId)))
                        }
                    }

                    description.appendText("with id: $outerIdDescription and neasted id: $nestedIdDescription")
                }

                override fun matchesSafely(view: View): Boolean {

                    resources = view.resources
                    if (childView == null) {
                        val recyclerView =
                                view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                        if (recyclerView.id == recyclerViewId) {
                            val desiredRecycler =
                                    recyclerView.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<View>(nestedScrollViewId)
                            if (desiredRecycler is RecyclerView) {
                                childView = desiredRecycler.findViewHolderForAdapterPosition(nestedScrollViewPosition)?.itemView
                            } else {
                                return false
                            }

                        } else {
                            return false
                        }
                    }

                    return if (targetViewId == -1) {
                        view === childView
                    } else {
                        val targetView = childView!!.findViewById<View>(targetViewId)
                        view === targetView
                    }
                }
            }
}