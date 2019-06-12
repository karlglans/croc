import React from 'react';
import PropTypes from 'prop-types';

import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";

const deafultNoAnswers = { count: [0, 0] }

class AnswersSummaryYesNoQuestion extends React.Component {

  componentDidMount() {
    const { number, content } = this.props;
    const answersCountList = content || deafultNoAnswers;
    const chart = am4core.create('chart' + number, am4charts.XYChart);

    chart.data = [{
      'category': 'Yes',
      'value': answersCountList.count[0]
    }, {
      'category': 'No',
      'value': answersCountList.count[1]
    }];

    const categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = 'category';

    chart.yAxes.push(new am4charts.ValueAxis());

    var series = chart.series.push(new am4charts.ColumnSeries());
    series.dataFields.valueY = 'value';
    series.dataFields.categoryX = 'category';
    series.name = 'Answer';
    this.chart = chart;
  }

  componentWillUnmount() {
    if (this.chart) {
      this.chart.dispose();
    }
  }

  render() {
    const { number } = this.props;
    return (
      <div style={{width: 400}} id={'chart' + number} />
    );
  }
};

AnswersSummaryYesNoQuestion.propTypes = {
  content: PropTypes.object
};

export default AnswersSummaryYesNoQuestion;
